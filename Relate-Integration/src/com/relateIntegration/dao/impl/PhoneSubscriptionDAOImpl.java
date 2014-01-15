package com.relateIntegration.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.relateIntegration.dao.PhoneSubscriptionDAO;
import com.relateIntegration.user.model.PhoneSubscription;
import com.relateIntegration.util.PhoneSubscriptionRowMapper;

public class PhoneSubscriptionDAOImpl extends JdbcDaoSupport implements PhoneSubscriptionDAO 
{

	public PhoneSubscription getPhoneSubscription(long phoneNumber) 
	{
		String sql = "SELECT * FROM PHONE_SUBSCRIPTION WHERE PHONE_NUMBER = " + phoneNumber ;
		List<PhoneSubscription> phoneSubscriptions= getJdbcTemplate().query(sql, new PhoneSubscriptionRowMapper());
		if(phoneSubscriptions != null && !phoneSubscriptions.isEmpty())
			return phoneSubscriptions.iterator().next();
		else
			return null;
	}

	public boolean insertPhoneSubscription(PhoneSubscription phoneSubscription) 
	{
		String sql = "INSERT INTO PHONE_SUBSCRIPTION ( PHONE_NUMBER, PHONE_OPT_IN_STATUS ,LAST_UPDATED_TIME ) VALUES ( ?, ?, ?)";
		int rowsAffected = getJdbcTemplate().update(sql, phoneSubscription.getPhoneNumber() , phoneSubscription.getPhoneOptInStatus() , phoneSubscription.getLastUpdatedTime());
		if(rowsAffected<=0)
		{ return false; }
		return true;
	}

	public boolean updatePhoneSubscription(PhoneSubscription phoneSubscription) 
	{
		String sql = "UPDATE PHONE_SUBSCRIPTION SET PHONE_OPT_IN_STATUS = ?, LAST_UPDATED_TIME = ? WHERE PHONE_NUMBER = ? ";
		int rowsAffected = getJdbcTemplate().update(sql,phoneSubscription.getPhoneOptInStatus() , phoneSubscription.getLastUpdatedTime(), phoneSubscription.getPhoneNumber());
		if(rowsAffected<=0)
			return false;
		else
			return true;
	}

}
