package com.rim.integration;

/**
 * @author amodkunte
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.rim.integration.imports.customer.CustomerProcessor;
import com.rim.integration.imports.customer.config.CustomerConfig;
import com.rim.integration.imports.email.EmailProcessor;
import com.rim.integration.imports.email.config.EmailConfig;
import com.rim.integration.imports.phone.PhoneProcessor;
import com.rim.integration.imports.phone.config.PhoneConfig;

public class ImpexExecutor {

	/**
	 * @param args
	 */
	private static String operationType;
	private static String dataType;
	private static String propPath;
	private static Properties props = new Properties();

	public static void main(String[] args) 
	{
		int success = 0;
		Date date = new Date();
		System.out.println("Starting process at: " + date);
		if (args.length != 3) 
		{
			System.out.println("Usage e.g.: import customer <path_to_cust_prop>");
			System.exit(0);
		}
		
		operationType = args[0].trim();
		if (!operationType.equalsIgnoreCase("import") && !operationType.equalsIgnoreCase("export")) 
		{
			System.out.println("Valid Operation Types are import | export");
			System.exit(0);
		}

		dataType = args[1].trim();
		if (!dataType.equalsIgnoreCase("customer") && !dataType.equalsIgnoreCase("emailfeed") && !dataType.equalsIgnoreCase("phonefeed")) 
		{
			System.out.println("Valid Data Types are | customer | emailfeed | phonefeed |");
			System.exit(0);
		}

		propPath = args[2].trim();
		File propFile = new File(propPath);
		if (!propFile.exists()) 
		{
			System.out.println("Properties File " + propPath + " does not exist.");
			System.exit(0);
		} 
		else 
		{
			try {
				props.load(new FileInputStream(propFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (operationType.equalsIgnoreCase("import")) 
		{
			if (dataType.equalsIgnoreCase("customer")) 
			{
				CustomerConfig config = new CustomerConfig();
				config.setPathToRead(props.getProperty("customer.read.dir.path"));
				config.setPathToWrite(props.getProperty("customer.write.dir.path"));
				config.setArchivePath(props.getProperty("customer.archive.path"));
				config.setFilePrefix(props.getProperty("customer.file.prefix"));
				config.setAddressLine1Column(props.getProperty("customer.csv.addr1.column"));
				config.setAddressLine2Column(props.getProperty("customer.csv.addr2.column"));
				config.setCityColumn(props.getProperty("customer.csv.city.column"));
				config.setCountryColumn(props.getProperty("customer.csv.country.column"));
				config.setEmailColumn(props.getProperty("customer.csv.email.column"));
				config.setTimeStampColumn(props.getProperty("customer.csv.timestamp.column"));
				config.setEmailOptInStatusColumn(props.getProperty("customer.csv.emailoptinstatus.column"));
				config.setFirstNameColumn(props.getProperty("customer.csv.fname.column"));
				config.setLastNameColumn(props.getProperty("customer.csv.lname.column"));
				config.setPhoneColumn(props.getProperty("customer.csv.phone.column"));
				config.setStateColumn(props.getProperty("customer.csv.state.column"));
				config.setZipcodeColumn(props.getProperty("customer.csv.zipcode.column"));
				config.setDateFormat(props.getProperty("customer.csv.dateformat"));
				config.setDelim(props.getProperty("customer.csv.delim"));
				config.setHeaderIncluded(Boolean.parseBoolean(props.getProperty("customer.csv.headerincluded")));
				CustomerProcessor processor = new CustomerProcessor();
				success = processor.process(config);

			}
			if (dataType.equalsIgnoreCase("emailfeed")) 
			{
				EmailConfig config = new EmailConfig();
				config.setPathToRead(props.getProperty("emailfeed.read.dir.path"));
				config.setPathToWrite(props.getProperty("emailfeed.write.dir.path"));
				config.setArchivePath(props.getProperty("emailfeed.archive.path"));
				config.setFilePrefix(props.getProperty("emailfeed.file.prefix"));
				config.setEmailColumn(props.getProperty("emailfeed.csv.email.column"));
				config.setEmailOptInStatusColumn(props.getProperty("emailfeed.csv.emailoptinstatus.column"));
				config.setTimeStampColumn(props.getProperty("emailfeed.csv.timestamp.column"));
				config.setDateFormat(props.getProperty("emailfeed.csv.dateformat"));
				config.setDelim(props.getProperty("emailfeed.csv.delim"));
				config.setHeaderIncluded(Boolean.parseBoolean(props.getProperty("emailfeed.csv.headerincluded")));
				EmailProcessor processor = new EmailProcessor();
				success = processor.process(config);

			}
			if (dataType.equalsIgnoreCase("phonefeed")) 
			{
				PhoneConfig config = new PhoneConfig();
				config.setPathToRead(props.getProperty("phonefeed.read.dir.path"));
				config.setPathToWrite(props.getProperty("phonefeed.write.dir.path"));
				config.setArchivePath(props.getProperty("phonefeed.archive.path"));
				config.setFilePrefix(props.getProperty("phonefeed.file.prefix"));
				config.setPhoneColumn(props.getProperty("phonefeed.csv.phone.column"));
				config.setPhoneOptInStatusColumn(props.getProperty("phonefeed.csv.phoneoptinstatus.column"));
				config.setTimeStampColumn(props.getProperty("phonefeed.csv.timestamp.column"));
				config.setDateFormat(props.getProperty("phonefeed.csv.dateformat"));
				config.setDelim(props.getProperty("phonefeed.csv.delim"));
				config.setHeaderIncluded(Boolean.parseBoolean(props.getProperty("phonefeed.csv.headerincluded")));
				PhoneProcessor processor = new PhoneProcessor();
				success = processor.process(config);

			}

		} else {
			//TODO : When export utility is required.
		}

		if (success == 0)
			System.out.println("Process completed successfully");
		else
			System.out.println("Process completed with errors");
		date = new Date();
		System.out.println("Ending process at: " + date);
	}

}
