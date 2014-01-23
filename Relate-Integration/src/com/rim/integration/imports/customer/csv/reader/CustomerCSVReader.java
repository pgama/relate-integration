package com.rim.integration.imports.customer.csv.reader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvReader;
import com.relateIntegration.user.model.RIMUser;
import com.rim.integration.imports.customer.config.CustomerConfig;


public class CustomerCSVReader {

	public List<RIMUser> readData(CustomerConfig config, File csvfile)
	{
		List<RIMUser> customers = new ArrayList<RIMUser>();
		HashMap<String,RIMUser> customersMap = new HashMap<String, RIMUser>();

		try 
		{
			CsvReader reader=new CsvReader(csvfile.getAbsolutePath());
			//reader.setDelimiter(config.getDelim().charAt(0));
			reader.setDelimiter("\t".charAt(0));
			reader.readHeaders();
			
			RIMUser customer = null;
			int counter = 1;
			
			while(reader.readRecord())
			{
				try
				{
					customer = new RIMUser();
					customer.setFirstName(reader.get(config.getFirstNameColumn()));
					customer.setLastName(reader.get(config.getLastNameColumn()));
					customer.setAddressLine1(reader.get(config.getAddressLine1Column()));
					customer.setAddressLine2(reader.get(config.getAddressLine2Column()));
					customer.setCity(reader.get(config.getCityColumn()));
					customer.setState(reader.get(config.getStateColumn()));
					customer.setZipCode(reader.get(config.getZipcodeColumn()));
					customer.setCountry(reader.get(config.getCountryColumn()));
					customer.setEmail(reader.get(config.getEmailColumn()));
					customer.setPhoneNumber(reader.get(config.getPhoneColumn()));
					customer.setEmailOptInStatus(reader.get(config.getEmailOptInStatusColumn()));
					SimpleDateFormat sdf = new SimpleDateFormat(config.getDateFormat());
					Date timeStamp = sdf.parse(reader.get(config.getTimeStampColumn()));
					customer.setLastUpdatedTime(timeStamp);
					
					if(customersMap.containsKey(customer.getEmail()))
					{
						RIMUser userFromMap = customersMap.get(customer.getEmail());
						if(userFromMap !=null && userFromMap.getLastUpdatedTime()!=null && customer.getLastUpdatedTime()!=null && customer.getLastUpdatedTime().compareTo(userFromMap.getLastUpdatedTime()) > 0)
							customersMap.put(customer.getEmail(),customer);
					}
					else
					{
						customersMap.put(customer.getEmail(),customer);
					}
					counter++;
					
				}
				catch(Exception e)
				{
					//e.printStackTrace();
				}
			}
			System.out.println("Completed reading data for records:"+counter);
			reader.close();
		} 
		catch (IOException e) 
		{
			//e.printStackTrace();
		}
		
		customers.addAll(customersMap.values());
		return customers;
	}

}
