package com.relateIntegration.relate.webservice.commands;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.context.ApplicationContext;

import com.relateIntegration.dao.RIMUserDAO;
import com.relateIntegration.dao.support.DAOManager;
import com.relateIntegration.relate.webservice.CustomerWebServiceManager;
import com.relateIntegration.services.response.AddOrUpdateResponse;
import com.relateIntegration.user.model.RIMUser;
import com.relateIntegration.util.RIMConstants;
import com.rim.integration.utils.ApplicationContextProvider;

public class UpdateCustomerEmailCommand 
{	
	public static final String noCustomer ="noCustomer";
	public static final String singleCustomer ="singleCustomer";
	public static final String multipleCustomers ="multipleCustomers";
	
	public static AddOrUpdateResponse updateCustomerEmailService(Map<String, Object> attributes) 
	{
		System.out.println("update customer email Webservice called ");
		
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		DAOManager daoManager = (DAOManager)ctx.getBean("daoManager");
		RIMUserDAO rimUserDAO = daoManager.getRimUserDAO();
		
		
		AddOrUpdateResponse response =new AddOrUpdateResponse();
		String responseId ="";
		
		if(attributes!=null && attributes.size()>0 && attributes.containsKey(RIMConstants.RequestMapping.OLD_EMAIL_KEY) && attributes.containsKey(RIMConstants.RequestMapping.NEW_EMAIL_KEY))
		{
			String oldEmailLookupStatusFromRelate = noCustomer;
			Map<String , Object> existingCustomerAtrributes = new HashMap<String, Object>();//existingCustomerAtrributes --> map for existing customer data from the relate
			
			Date timeStampFromRequest = new Date();
			if(attributes.containsKey(RIMConstants.RequestMapping.TIMESTAMP_KEY))
			{
				try 
					{timeStampFromRequest = DateUtil.parseDate(attributes.get(RIMConstants.RequestMapping.TIMESTAMP_KEY).toString());} 
				catch (DateParseException e) 
					{
						//System.out.println("Error occured while parsing");
					}
			}
			//Lookup to module
			RIMUser user =null;
			int relateIntegrationId = 0;
			user = rimUserDAO.getRIMUserByEmail(attributes.get(RIMConstants.RequestMapping.OLD_EMAIL_KEY).toString());
			if(user==null)
			{
/*				rimUserDAO.insertRIMUserWithEmail(moduleTableData.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());
				user = rimUserDAO.getRIMUserByEmail(moduleTableData.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());*/
				
				response.setRelateModuleId(null);
				response.setSuccess(false);
				response.setErrorMessage("The Old Email does not exist on Module");
				return response; 
			}
			else
			{
				relateIntegrationId = user.getRelateIntegrationId();
				if(user.getLastUpdatedTime().compareTo(timeStampFromRequest) > 0)
				{
					response.setErrorMessage("TimeStamp from request is older than what relate has.");
					response.setSuccess(false);
					return response;	
				}
			}	
			
			//Lookup to Relate with only old email
			Map<String , Object> searchWithEmailMap = new HashMap<String, Object>();
			searchWithEmailMap.put(RIMConstants.RequestMapping.EMAIL_KEY, attributes.get(RIMConstants.RequestMapping.OLD_EMAIL_KEY).toString());
			List<Object> customerList = CustomerWebServiceManager.searchCustomers(searchWithEmailMap , 0 );
			if(customerList!=null && customerList.size() == 1)
				oldEmailLookupStatusFromRelate = singleCustomer;
			if(customerList!=null && customerList.size() > 1)
				oldEmailLookupStatusFromRelate = multipleCustomers;


			if(oldEmailLookupStatusFromRelate.equals(singleCustomer))
				{
					if(customerList.iterator().hasNext())
					existingCustomerAtrributes=(HashMap<String, Object>)customerList.iterator().next();	
				}
			else if(oldEmailLookupStatusFromRelate.equals(multipleCustomers))
				{		
					//merge customers 
					String mergeCustomerId = CustomerWebServiceManager.mergeCustomers(customerList , String.valueOf(relateIntegrationId)); 
					
					//Lookup for new merged customer and update the relate with attributes
					if( mergeCustomerId != null && !mergeCustomerId.isEmpty())
					{
						Map<String , Object> mergeCustomerSearchMap = new HashMap<String, Object>();
						mergeCustomerSearchMap.put(RIMConstants.RequestMapping.CUSTOMER_KEY, mergeCustomerId);
						List<Object> afterMergecustomerList = CustomerWebServiceManager.searchCustomers(mergeCustomerSearchMap , 0 );
						if(afterMergecustomerList!=null && afterMergecustomerList.iterator().hasNext())
								existingCustomerAtrributes=(HashMap<String, Object>)afterMergecustomerList.iterator().next();
	
					}	
				}
				
			existingCustomerAtrributes.put(RIMConstants.RequestMapping.EMAIL_KEY, attributes.get(RIMConstants.RequestMapping.NEW_EMAIL_KEY).toString());
			responseId = CustomerWebServiceManager.addOrUdateCustomer(existingCustomerAtrributes ,relateIntegrationId);	
			 
			 //Update ModuleDB
			Map<String , Object> moduleTableData = new HashMap<String, Object>(); //moduleTableData --> map for updating in the module db.
			moduleTableData.put(RIMConstants.CustomerInformation.EMAIL_KEY, attributes.get(RIMConstants.RequestMapping.NEW_EMAIL_KEY).toString());
			if(user!=null)
				rimUserDAO.updateRIMUserWithEmail(moduleTableData, attributes.get(RIMConstants.RequestMapping.OLD_EMAIL_KEY).toString());
			
			//after the email is updated lookup with new email
			Map<String , Object> searchWithNewEmailMap = new HashMap<String, Object>();
			searchWithNewEmailMap.put(RIMConstants.RequestMapping.EMAIL_KEY, attributes.get(RIMConstants.RequestMapping.NEW_EMAIL_KEY).toString());
			List<Object> newEmailCustomerList = CustomerWebServiceManager.searchCustomers(searchWithNewEmailMap , 0 );
			if(newEmailCustomerList!=null && newEmailCustomerList.size() > 1)
			{
				CustomerWebServiceManager.mergeCustomers(newEmailCustomerList , String.valueOf(relateIntegrationId)); 	
			}
	
			if(responseId != null && !responseId.isEmpty())
			{
				if(relateIntegrationId != 0)
					response.setRelateModuleId(String.valueOf(relateIntegrationId));
				else
					response.setRelateModuleId(null);
				
				response.setSuccess(true);
				response.setErrorMessage(null);
			}
			else
			{
				response.setRelateModuleId(null);
				response.setSuccess(false);
				response.setErrorMessage("Failed to Update from Module to Relate");
			}
		}
		else
		{
			response.setRelateModuleId(null);
			response.setSuccess(false);
			response.setErrorMessage("Request do not have sufficient data");
			return response; 
		}
		return response; 
	}
	
}
