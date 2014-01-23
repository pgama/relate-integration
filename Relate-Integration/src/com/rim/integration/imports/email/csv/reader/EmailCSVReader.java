package com.rim.integration.imports.email.csv.reader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvReader;
import com.relateIntegration.user.model.RIMUser;
import com.rim.integration.imports.email.config.EmailConfig;

public class EmailCSVReader 
{
	public List<RIMUser> readData(EmailConfig config, File csvfile)
	{
		List<RIMUser> emailCustomers = new ArrayList<RIMUser>();

		try 
		{
			CsvReader reader=new CsvReader(csvfile.getAbsolutePath());
			//reader.setDelimiter(config.getDelim().charAt(0));
			reader.setDelimiter("\t".charAt(0));
			reader.readHeaders();
			
			RIMUser emailCustomer = null;
			int counter = 1;
			
			while(reader.readRecord())
			{
				try
				{
					emailCustomer = new RIMUser();
					emailCustomer.setEmail(reader.get(config.getEmailColumn()));
					emailCustomer.setEmailOptInStatus(reader.get(config.getEmailOptInStatusColumn()));

					SimpleDateFormat sdf = new SimpleDateFormat(config.getDateFormat());
					Date timeStamp = sdf.parse(reader.get(config.getTimeStampColumn()));
					emailCustomer.setLastUpdatedTime(timeStamp);
					
					emailCustomers.add(emailCustomer);
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
			e.printStackTrace();
		}		
		return emailCustomers;
	}


}
