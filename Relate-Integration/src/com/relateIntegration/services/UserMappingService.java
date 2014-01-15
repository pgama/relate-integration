package com.relateIntegration.services;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.relateIntegration.services.response.AddOrUpdateResponse;
import com.relateIntegration.services.response.LookupResponse;
import com.relateIntegration.services.response.RIMResponse;

@Consumes("application/json")
@Produces("application/json")
public interface UserMappingService {
	
	@POST
	@Path("/addOrUpdateCustomerData/")
	public AddOrUpdateResponse addOrUpdateCustomerData(Map<String , Object> attributes);
	
	@POST
	@Path("/updateCustomerEmail/")
	public AddOrUpdateResponse updateCustomerEmail(Map<String , Object> attributes);
	
	@POST
	@Path("/lookupCustomer/")
	public LookupResponse lookupCustomer(Map<String, Object> attributes);
	
	@POST
	@Path("/updatePhoneOptInStatus/")
	public RIMResponse updatePhoneOptInStatus(Map<String, Object> attributes);

}
