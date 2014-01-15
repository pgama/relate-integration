package com.relateIntegration.relate.webservice.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relateIntegration.relate.webservice.CustomerWebServiceManager;
import com.relateIntegration.services.response.LookupResponse;
import com.relateIntegration.util.RIMConstants;

public class LookupCustomerCommand 
{
	public static LookupResponse lookupCustomer(Map<String, Object> attributes)
	{
		LookupResponse response =new LookupResponse();
		List<Object> customerList=null;
		
		if(attributes!=null && attributes.size()>0)
		{
			System.out.println("update customer email Webservice called ");
			Map<String , Object> existingCustomerAtrributes = new HashMap<String, Object>();
			
			int relateModuleId =0;
			if(attributes.containsKey(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE))
			{
				relateModuleId = Integer.parseInt(attributes.get(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE).toString());
			}
			
			customerList = CustomerWebServiceManager.searchCustomers(attributes,relateModuleId);
		}
		
		else
		{
			response.setSuccess(false);
			response.setErrorMessage("Request is Empty.");
		}
		response.setCustomerList(customerList);
		response.setSuccess(true);
		return response;
		
	}
}
