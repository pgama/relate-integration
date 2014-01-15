package com.rim.integration.imports.phone.csv.reader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvReader;
import com.relateIntegration.user.model.PhoneSubscription;
import com.rim.integration.imports.phone.config.PhoneConfig;

public class PhoneCSVReader 
{
	public List<PhoneSubscription> readData(PhoneConfig config, File csvfile)
	{
		List<PhoneSubscription> phoneCustomers = new ArrayList<PhoneSubscription>();

		try 
		{
			CsvReader reader=new CsvReader(csvfile.getAbsolutePath());
			reader.setDelimiter(config.getDelim().charAt(0));
			reader.readHeaders();
			
			PhoneSubscription phoneCustomer = null;
			int counter = 1;
			
			while(reader.readRecord())
			{
				try
				{
					phoneCustomer = new PhoneSubscription();
					phoneCustomer.setPhoneNumber(Long.valueOf(reader.get(config.getPhoneColumn())));
					phoneCustomer.setPhoneOptInStatus(reader.get(config.getPhoneOptInStatusColumn()));

					SimpleDateFormat sdf = new SimpleDateFormat(config.getDateFormat());
					Date timeStamp = sdf.parse(reader.get(config.getTimeStampColumn()));
					phoneCustomer.setLastUpdatedTime(timeStamp);
					
					
					phoneCustomers.add(phoneCustomer);
					counter++;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("Completed reading data for records:"+counter);
			reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
		return phoneCustomers;
	}

}
