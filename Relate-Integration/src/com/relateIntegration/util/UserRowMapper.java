package com.relateIntegration.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.relateIntegration.user.model.RIMUser;

public class UserRowMapper implements RowMapper<RIMUser>{

	public RIMUser mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		RIMUser user=new RIMUser();
		
		user.setRelateIntegrationId(resultSet.getInt(1));
		user.setEmail(resultSet.getString(2));
		user.setFirstName(resultSet.getString(3));
		user.setLastName(resultSet.getString(4));
		user.setAddressLine1(resultSet.getString(5));
		user.setAddressLine2(resultSet.getString(6));
		user.setCity(resultSet.getString(7));
		user.setState(resultSet.getString(8));
		user.setCountry(resultSet.getString(9));
		user.setZipCode(resultSet.getString(10));
		user.setEmailOptInStatus(resultSet.getString(11));
		user.setPhoneNumber(resultSet.getString(12));
		user.setLastUpdatedTime(resultSet.getDate(13));
		user.setExistInRelate(resultSet.getString(14));
		user.setObsession_clothes(resultSet.getString(15));
		user.setObsession_accessories(resultSet.getString(16));
		user.setObsession_shoes(resultSet.getString(17));
		user.setObsession_fashion(resultSet.getString(18));
		
		return user;
	}

}
