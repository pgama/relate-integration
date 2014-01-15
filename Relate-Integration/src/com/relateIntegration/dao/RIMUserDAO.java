package com.relateIntegration.dao;

import java.util.List;
import java.util.Map;

import com.relateIntegration.user.model.RIMUser;

public interface RIMUserDAO 
{
	
	public RIMUser getRIMUserByRelateIntegrationId(int relateIntegrationId);
	public RIMUser getRIMUserByEmail(String email);
	public List<RIMUser> getRIMUsers(Map<String, Object> matchCriteria);
	public boolean insertRIMUser(RIMUser user);
	public boolean insertRIMUserWithEmail(String email);
	public boolean updateRIMUserWithIntegrationId(Map<String, Object> attributes , int OCPRelateIntegrationId);
	public boolean updateRIMUserWithEmail(Map<String, Object> attributes , String email);
	
}
