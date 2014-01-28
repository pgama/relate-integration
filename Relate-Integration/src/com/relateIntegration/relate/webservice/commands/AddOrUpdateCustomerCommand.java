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
import com.relateIntegration.util.CommerceUtil;
import com.relateIntegration.util.RIMConstants;
import com.rim.integration.utils.ApplicationContextProvider;

public class AddOrUpdateCustomerCommand 
{
	public static final String noCustomer ="noCustomer";
	public static final String singleCustomer ="singleCustomer";
	public static final String multipleCustomers ="multipleCustomers";
	
	public static AddOrUpdateResponse addOrUpdateCustomerService(Map<String, Object> attributes)
	{
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		DAOManager daoManager = (DAOManager)ctx.getBean("daoManager");
		return addOrUpdateCustomerService(attributes,daoManager);
	}
	
	public static AddOrUpdateResponse addOrUpdateCustomerService(Map<String, Object> attributes,DAOManager daoManager )
	{
		System.out.println("add or update customer Webservice called ");
		
		RIMUserDAO rimUserDAO = daoManager.getRimUserDAO();
		
		AddOrUpdateResponse response =new AddOrUpdateResponse();
		String responseId ="";
		
		if(attributes!=null && attributes.size()>0 && attributes.containsKey(RIMConstants.RequestMapping.EMAIL_KEY) && !attributes.get(RIMConstants.RequestMapping.EMAIL_KEY).toString().equals("") )
		{
			String customerLookupStatusFromRelate = noCustomer;
			Map<String , Object> moduleTableData = CommerceUtil.getModuleTableDataMapForCustomerInformation(attributes); //moduleTableData --> map for updating in the module db.
			Map<String , Object> existingCustomerAtrributes = new HashMap<String, Object>();//existingCustomerAtrributes --> map for existing customer data from the relate
			
			//Default consider EXIST_IN_MODULE column value for the module table as "Y"
			moduleTableData.put(RIMConstants.CustomerInformation.EXIST_IN_RELATE_KEY, "Y");
			
			Date timeStampFromRequest = new Date();
			if(attributes.containsKey(RIMConstants.RequestMapping.TIMESTAMP_KEY))
			{
				try 
					{timeStampFromRequest = DateUtil.parseDate(attributes.get(RIMConstants.RequestMapping.TIMESTAMP_KEY).toString());} 
				catch (DateParseException e) 
					{
						//System.out.println("Error occured while parsing date");
					}
					
			}
			//Lookup to module
			RIMUser user =null;
			user = rimUserDAO.getRIMUserByEmail(moduleTableData.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());
			if(user==null)
			{
				rimUserDAO.insertRIMUserWithEmail(moduleTableData.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());
				user = rimUserDAO.getRIMUserByEmail(moduleTableData.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());
			}
			else
			{
				if(user.getLastUpdatedTime()!=null && user.getLastUpdatedTime().compareTo(timeStampFromRequest) > 0)
				{
					response.setErrorMessage("TimeStamp from request is older than what relate has.");
					response.setSuccess(false);
					return response;	
				}
			}
			
			int relateIntegrationId = 0;
			if(user!=null)
				relateIntegrationId = user.getRelateIntegrationId();
			
			//Lookup to Relate with only email
			Map<String , Object> searchWithEmailMap = new HashMap<String, Object>();
			searchWithEmailMap.put(RIMConstants.RequestMapping.EMAIL_KEY, attributes.get(RIMConstants.RequestMapping.EMAIL_KEY).toString());
			List<Object> customerList = CustomerWebServiceManager.searchCustomers(searchWithEmailMap , 0 );
			if(customerList!=null && customerList.size() == 1)
				customerLookupStatusFromRelate = singleCustomer;
			if(customerList!=null && customerList.size() > 1)
				customerLookupStatusFromRelate = multipleCustomers;

			if(customerLookupStatusFromRelate.equals(noCustomer))
			{
				 responseId = CustomerWebServiceManager.addOrUdateCustomer(attributes , relateIntegrationId);
				 if(responseId == null || responseId.isEmpty())
					 moduleTableData.put(RIMConstants.CustomerInformation.EXIST_IN_RELATE_KEY, "N");
			}
			else if(customerLookupStatusFromRelate.equals(singleCustomer))
			{
				if(customerList.iterator().hasNext())
				existingCustomerAtrributes=(HashMap<String, Object>)customerList.iterator().next();
				existingCustomerAtrributes.putAll(attributes);
				attributes = existingCustomerAtrributes;
				responseId = CustomerWebServiceManager.addOrUdateCustomer(attributes , relateIntegrationId);	
				
			}
			else if(customerLookupStatusFromRelate.equals(multipleCustomers))
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
						{
							existingCustomerAtrributes=(HashMap<String, Object>)afterMergecustomerList.iterator().next();
							existingCustomerAtrributes.putAll(attributes);
							attributes = existingCustomerAtrributes;
						}
					responseId = CustomerWebServiceManager.addOrUdateCustomer(attributes ,relateIntegrationId);	
				}
				
			}
				
			if(user!=null)
			rimUserDAO.updateRIMUserWithEmail(moduleTableData, moduleTableData.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());
			
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
			response.setErrorMessage("Request does not contain email"); 
		}		
		return response; 
	}

}
