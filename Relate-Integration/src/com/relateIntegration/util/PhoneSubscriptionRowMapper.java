package com.relateIntegration.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.relateIntegration.user.model.PhoneSubscription;

public class PhoneSubscriptionRowMapper implements RowMapper<PhoneSubscription> {

	public PhoneSubscription mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		PhoneSubscription PhoneSubscription=new PhoneSubscription();
		
		PhoneSubscription.setPhoneNumber(resultSet.getInt(1));
		PhoneSubscription.setPhoneOptInStatus(resultSet.getString(2));
		PhoneSubscription.setLastUpdatedTime(resultSet.getDate(3));
		return PhoneSubscription;
	}

}
