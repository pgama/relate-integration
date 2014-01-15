package com.relateIntegration.services.impl;

import java.util.Map;

import com.relateIntegration.relate.webservice.commands.AddOrUpdateCustomerCommand;
import com.relateIntegration.relate.webservice.commands.LookupCustomerCommand;
import com.relateIntegration.relate.webservice.commands.UpdateCustomerEmailCommand;
import com.relateIntegration.relate.webservice.commands.UpdatePhoneOptInStatusCommand;
import com.relateIntegration.services.UserMappingService;
import com.relateIntegration.services.response.AddOrUpdateResponse;
import com.relateIntegration.services.response.LookupResponse;
import com.relateIntegration.services.response.RIMResponse;


public class UserMappingServiceImpl implements UserMappingService
{
	public AddOrUpdateResponse addOrUpdateCustomerData(Map<String, Object> attributes) 
	{
		return AddOrUpdateCustomerCommand.addOrUpdateCustomerService(attributes);
	}
	
	public AddOrUpdateResponse updateCustomerEmail(Map<String, Object> attributes) 
	{
		return UpdateCustomerEmailCommand.updateCustomerEmailService(attributes);
	}
	
	public LookupResponse lookupCustomer(Map<String, Object> attributes) 
	{
		return LookupCustomerCommand.lookupCustomer(attributes);
	}

	public RIMResponse updatePhoneOptInStatus(Map<String, Object> attributes) 
	{
		return UpdatePhoneOptInStatusCommand.updatePhoneOptInStatus(attributes);
	}
	
	
	
}
