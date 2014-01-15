package com.relateIntegration.relate.webservice.commands;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.context.ApplicationContext;

import com.relateIntegration.dao.PhoneSubscriptionDAO;
import com.relateIntegration.dao.support.DAOManager;
import com.relateIntegration.relate.webservice.CustomerWebServiceManager;
import com.relateIntegration.services.response.RIMResponse;
import com.relateIntegration.user.model.PhoneSubscription;
import com.relateIntegration.util.RIMConstants;
import com.rim.integration.utils.ApplicationContextProvider;

public class UpdatePhoneOptInStatusCommand 
{
	public static RIMResponse updatePhoneOptInStatus(Map<String, Object> attributes) 
	{
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		DAOManager daoManager = (DAOManager)ctx.getBean("daoManager");
		return updatePhoneOptInStatus(attributes,daoManager);
	}

	public static RIMResponse updatePhoneOptInStatus(Map<String, Object> attributes,DAOManager daoManager) 
	{
		System.out.println("update phone optIn status Webservice called ");
		

		PhoneSubscriptionDAO phoneSubscriptionDAO = daoManager.getPhoneSubscriptionDAO();
		
		RIMResponse response =new RIMResponse();
		String responseId ="";
		
		if(attributes!=null && attributes.size()>0 && attributes.containsKey(RIMConstants.RequestMapping.PHONE_NUMBER_KEY) && !attributes.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString().equals(""))
		{
			String phoneNumber = "";
			Scanner in = new Scanner(attributes.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString()).useDelimiter("[^0-9]+");
			while (in.hasNext())
				{
					phoneNumber += in.next();
				}
			int numLen = phoneNumber.length();
			if(numLen > 10)
				phoneNumber = phoneNumber.substring(numLen-10,numLen);
			
			String phoneOptInStatus = "N" ;//default
			if(attributes.containsKey(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY).toString().equalsIgnoreCase("true"))
					phoneOptInStatus= "Y";
			}
				
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
			PhoneSubscription phoneSubscription =null;
			boolean existsInRelate = false;
			phoneSubscription = phoneSubscriptionDAO.getPhoneSubscription(Long.parseLong(phoneNumber));
			if(phoneSubscription == null)
			{
				phoneSubscription= new PhoneSubscription();
			}
			else
			{
				existsInRelate = true;
				if(phoneSubscription.getLastUpdatedTime().compareTo(timeStampFromRequest) > 0)
				{
					response.setErrorMessage("TimeStamp from request is older than what relate has.");
					response.setSuccess(false);
					return response;	
				}

			}
			
			phoneSubscription.setPhoneNumber(Long.parseLong(phoneNumber));
			phoneSubscription.setPhoneOptInStatus(phoneOptInStatus);
			phoneSubscription.setLastUpdatedTime(timeStampFromRequest);
			
			//Lookup to Relate with only Phone Only
			Map<String , Object> searchWithPhoneMap = new HashMap<String, Object>();
			searchWithPhoneMap.put(RIMConstants.RequestMapping.PHONE_NUMBER_KEY, phoneNumber);
			List<Object> customerList = CustomerWebServiceManager.searchCustomers(searchWithPhoneMap , 0 );
			
			if(customerList!=null && customerList.size() > 0)
			{
				Iterator<Object> custIter = customerList.iterator();
				while(custIter.hasNext())
				{
					Map<String , Object> temp = (HashMap<String, Object>)custIter.next();
					Map<String,Object> relateRequest =  new HashMap<String, Object>();
					if(temp.containsKey(RIMConstants.RequestMapping.CUSTOMER_KEY))
					{
						relateRequest.put(RIMConstants.RequestMapping.CUSTOMER_KEY, temp.get(RIMConstants.RequestMapping.CUSTOMER_KEY).toString());
						if(attributes.containsKey(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY))
							relateRequest.put(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY, attributes.get(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY).toString());
						else
							relateRequest.put(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY, "false");
						
						responseId = CustomerWebServiceManager.addOrUdateCustomer(relateRequest, 0);
					}
				}	
			}
			else
			{
				Map<String,Object> relateRequest =  new HashMap<String, Object>();
				relateRequest.put(RIMConstants.RequestMapping.PHONE_NUMBER_KEY,phoneNumber);
				if(attributes.containsKey(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY))
					relateRequest.put(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY, attributes.get(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY).toString());
				else
					relateRequest.put(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY, "false");
				
				responseId = CustomerWebServiceManager.addOrUdateCustomer(relateRequest, 0);
			}
			
			//Update Module DB 
			if(existsInRelate)
				phoneSubscriptionDAO.updatePhoneSubscription(phoneSubscription);
			else
				phoneSubscriptionDAO.insertPhoneSubscription(phoneSubscription);
			
			if(responseId != null && !responseId.isEmpty())
			{
				response.setSuccess(true);
				response.setErrorMessage(null);
			}
			else
			{
				response.setSuccess(false);
				response.setErrorMessage("Failed to Update from Module to Relate");
			}
		}
		else
		{
			response.setSuccess(false);
			response.setErrorMessage("Request does not sufficient data");
			return response; 
		}
		
		return response; 
	}

}
