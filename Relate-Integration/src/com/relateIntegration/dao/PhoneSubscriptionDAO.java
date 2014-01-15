package com.relateIntegration.dao;

import com.relateIntegration.user.model.PhoneSubscription;

public interface PhoneSubscriptionDAO 
{
	public PhoneSubscription getPhoneSubscription(long phoneNumber);
	public boolean insertPhoneSubscription(PhoneSubscription phoneSubscription);
	public boolean updatePhoneSubscription(PhoneSubscription phoneSubscription);
	
}
