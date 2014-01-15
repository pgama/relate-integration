package com.relateIntegration.dao.support;

import com.relateIntegration.dao.PhoneSubscriptionDAO;
import com.relateIntegration.dao.RIMUserDAO;

public class DAOManager 
{
	RIMUserDAO rimUserDAO;
	PhoneSubscriptionDAO phoneSubscriptionDAO;
	
	
	public RIMUserDAO getRimUserDAO() {
		return rimUserDAO;
	}
	public void setRimUserDAO(RIMUserDAO rimUserDAO) {
		this.rimUserDAO = rimUserDAO;
	}
	public PhoneSubscriptionDAO getPhoneSubscriptionDAO() {
		return phoneSubscriptionDAO;
	}
	public void setPhoneSubscriptionDAO(PhoneSubscriptionDAO phoneSubscriptionDAO) {
		this.phoneSubscriptionDAO = phoneSubscriptionDAO;
	}

}
