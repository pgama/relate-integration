package com.relateIntegration.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.relateIntegration.dao.RIMUserDAO;
import com.relateIntegration.user.model.RIMUser;
import com.relateIntegration.util.RIMConstants;
import com.relateIntegration.util.UserRowMapper;

public class RIMUserDAOImpl extends JdbcDaoSupport implements RIMUserDAO{

	
	public RIMUser getRIMUserByRelateIntegrationId(int relateIntegrationId) 
	{
		String sql = "SELECT * FROM CUSTOMER_INFORMATION WHERE RELATE_INTEGRATION_ID = " + relateIntegrationId ;
		List<RIMUser> users= getJdbcTemplate().query(sql, new UserRowMapper());
		
		if(users!=null && !users.isEmpty())
		{
			return users.iterator().next();
		}
		else
		{
			return null;
		}
	}

	public RIMUser getRIMUserByEmail(String email) {
		
		String sql = "SELECT * FROM CUSTOMER_INFORMATION WHERE EMAIL = '" + email + "'";
		List<RIMUser> users= getJdbcTemplate().query(sql, new UserRowMapper());
		if(users!=null && !users.isEmpty())
		{
			return users.iterator().next();
		}
		else
		{
			return null;
		}
	}
	
	public List<RIMUser> getRIMUsers(Map<String, Object> matchCriteria) {
		
		Object parameters []= new Object[matchCriteria.size()];
		int index=0;
		
		boolean firstElementInMap = true;
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT * FROM CUSTOMER_INFORMATION WHERE ");

		// Append all of the column names we want in the where clause and save their respective values to parameters array
		for (Iterator<String> columnIterator =  matchCriteria.keySet().iterator(); columnIterator.hasNext();) {
			String columnName = columnIterator.next().toString();

			if (firstElementInMap) {
				firstElementInMap = false;
			} else {
				selectBuffer.append(" AND ");
			}
			selectBuffer.append(columnName);
			selectBuffer.append(" = ? ");
			parameters[index++]=matchCriteria.get(columnName);	
		}
		
		String sql = selectBuffer.toString();
		List<RIMUser> users= getJdbcTemplate().query(sql,parameters, new UserRowMapper());
		
		return users;
	}

	public boolean insertRIMUser(RIMUser user) 
	{
		String sql = "INSERT INTO CUSTOMER_INFORMATION "+
					 "( EMAIL, FIRST_NAME ,LAST_NAME ,ADDRESS_LINE_1, ADDRESS_LINE_2 , CITY , STATE , ZIP_CODE , EMAIL_OPT_STATUS, PHONE_NUMBER,  OBSESSION_CLOTHES , OBSESSION_ACCESSORIES , OBSESSION_SHOES , OBSESSION_FASHION, LAST_UPDATED_TIME, OCP_CUST_ID ) " +
					 "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		int rowsAffected = getJdbcTemplate().update(sql,
				user.getEmail(),user.getFirstName() , user.getLastName() , user.getAddressLine1() , user.getAddressLine2() , user.getCity() ,user.getState() ,user.getZipCode(), 
				user.getEmailOptInStatus() ,user.getPhoneNumber() , user.getObsession_clothes(),user.getObsession_accessories(),user.getObsession_shoes(),user.getObsession_fashion() ,user.getLastUpdatedTime(),user.getOcpCustId());

		if(rowsAffected<=0)
			{ return false; }
		
		return true;
			
	}
	
	public boolean insertRIMUserWithEmail(String email) 
	{
		String sql = "INSERT INTO CUSTOMER_INFORMATION "+
					 "( EMAIL) " +
					 "VALUES ( ? )";
		 
		int rowsAffected = getJdbcTemplate().update(sql,email);

		if(rowsAffected<=0)
			{ return false; }
		
		return true;
			
	}

	public boolean updateRIMUserWithIntegrationId(Map<String, Object> attributes , int relateIntegrationId) 
	{
		if(!attributes.containsKey(RIMConstants.CustomerInformation.LAST_UPDATED_TIME_KEY))
			attributes.put(RIMConstants.CustomerInformation.LAST_UPDATED_TIME_KEY , new Date());
		
		Object parameters []= new Object[attributes.size() + 1];
		int index=0;
		
		boolean commaLogicFlag = false;
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("UPDATE CUSTOMER_INFORMATION SET ");

		// Append all of the column names we want updated and save their respective values to parameters array
		for (Iterator<String> columnIterator = attributes.keySet().iterator(); columnIterator.hasNext();) {
			String columnName = columnIterator.next().toString();

				if (commaLogicFlag) {
					updateBuffer.append(", ");
				} else {
					commaLogicFlag = true;
				}

				updateBuffer.append(columnName);
				updateBuffer.append(" = ?");
				parameters[index++]=attributes.get(columnName);
		}
		
		// saving value for  parameter in where clause
		parameters[index++] = relateIntegrationId;

		// Append on the where clause to complete the SQL and build our statement
		updateBuffer.append(" WHERE RELATE_INTEGRATION_ID = ?");
		
		String sql =updateBuffer.toString();
		
		int rowsAffected = getJdbcTemplate().update(sql,parameters);
		
		if(rowsAffected<=0)
		{ return false; }
		
		return true;
	}
	
	public boolean updateRIMUserWithEmail(Map<String, Object> attributes , String email) 
	{
		if(!attributes.containsKey(RIMConstants.CustomerInformation.LAST_UPDATED_TIME_KEY))
			attributes.put(RIMConstants.CustomerInformation.LAST_UPDATED_TIME_KEY , new Date());
		
		Object parameters []= new Object[attributes.size() + 1];
		int index=0;
		
		boolean commaLogicFlag = false;
		StringBuffer updateBuffer = new StringBuffer();
		updateBuffer.append("UPDATE CUSTOMER_INFORMATION SET ");

		// Append all of the column names we want updated and save their respective values to parameters array
		for (Iterator<String> columnIterator = attributes.keySet().iterator(); columnIterator.hasNext();) {
			String columnName = columnIterator.next().toString();

				if (commaLogicFlag) {
					updateBuffer.append(", ");
				} else {
					commaLogicFlag = true;
				}

				updateBuffer.append(columnName);
				updateBuffer.append(" = ?");
				parameters[index++]=attributes.get(columnName);
		}
		
		// saving value for  parameter in where clause
		parameters[index++] = email;

		// Append on the where clause to complete the SQL and build our statement
		updateBuffer.append(" WHERE EMAIL = ?");
		
		String sql =updateBuffer.toString();
		
		int rowsAffected = getJdbcTemplate().update(sql,parameters);
		
		if(rowsAffected<=0)
		{ return false; }
		
		return true;
	}

}
