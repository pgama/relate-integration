package com.rim.integration;

/**
 * @author amodkunte
 *
 */

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.axis2.AxisFault;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.dtv.csx.webservices.customer.CustomerServicesApiServiceStub;
import com.relateIntegration.dao.impl.PhoneSubscriptionDAOImpl;
import com.relateIntegration.dao.impl.RIMUserDAOImpl;
import com.relateIntegration.dao.support.DAOManager;
import com.relateIntegration.relate.webservice.CustomerWebServiceManager;
import com.relateIntegration.util.DomUtil;
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
	private static Map props = new Properties();

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
			props=buildEnvironmentEntryMap(propFile);
			//getDatabase(propFile);
		}

		if (operationType.equalsIgnoreCase("import")) 
		{
			if (dataType.equalsIgnoreCase("customer")) 
			{
				CustomerConfig config = new CustomerConfig();
				config.setPathToRead(props.get("customer.read.dir.path").toString());
				config.setPathToWrite(props.get("customer.write.dir.path").toString());
				config.setArchivePath(props.get("customer.archive.path").toString());
				config.setFilePrefix(props.get("customer.file.prefix").toString());
				config.setAddressLine1Column(props.get("customer.csv.addr1.column").toString());
				config.setAddressLine2Column(props.get("customer.csv.addr2.column").toString());
				config.setCityColumn(props.get("customer.csv.city.column").toString());
				config.setCountryColumn(props.get("customer.csv.country.column").toString());
				config.setEmailColumn(props.get("customer.csv.email.column").toString());
				config.setTimeStampColumn(props.get("customer.csv.timestamp.column").toString());
				config.setEmailOptInStatusColumn(props.get("customer.csv.emailoptinstatus.column").toString());
				config.setFirstNameColumn(props.get("customer.csv.fname.column").toString());
				config.setLastNameColumn(props.get("customer.csv.lname.column").toString());
				config.setPhoneColumn(props.get("customer.csv.phone.column").toString());
				config.setStateColumn(props.get("customer.csv.state.column").toString());
				config.setZipcodeColumn(props.get("customer.csv.zipcode.column").toString());
				config.setOcpCustIdColumn(props.get("customer.csv.ocpcustid.column").toString());
				config.setDateFormat(props.get("customer.csv.dateformat").toString());
				config.setDelim(props.get("customer.csv.delim").toString());
				config.setHeaderIncluded(Boolean.parseBoolean(props.get("customer.csv.headerincluded").toString()));
				DAOManager daoManager = getDAOManager(propFile);
				CustomerProcessor processor = new CustomerProcessor();
				success = processor.process(config,daoManager);

			}
			if (dataType.equalsIgnoreCase("emailfeed")) 
			{
				EmailConfig config = new EmailConfig();
				config.setPathToRead(props.get("emailfeed.read.dir.path").toString());
				config.setPathToWrite(props.get("emailfeed.write.dir.path").toString());
				config.setArchivePath(props.get("emailfeed.archive.path").toString());
				config.setFilePrefix(props.get("emailfeed.file.prefix").toString());
				config.setEmailColumn(props.get("emailfeed.csv.email.column").toString());
				config.setEmailOptInStatusColumn(props.get("emailfeed.csv.emailoptinstatus.column").toString());
				config.setTimeStampColumn(props.get("emailfeed.csv.timestamp.column").toString());
				config.setDateFormat(props.get("emailfeed.csv.dateformat").toString());
				config.setDelim(props.get("emailfeed.csv.delim").toString());
				config.setHeaderIncluded(Boolean.parseBoolean(props.get("emailfeed.csv.headerincluded").toString()));
				DAOManager daoManager = getDAOManager(propFile);
				setCustomerServicesApiServiceStub();
				EmailProcessor processor = new EmailProcessor();
				success = processor.process(config, daoManager);

			}
			if (dataType.equalsIgnoreCase("phonefeed")) 
			{
				PhoneConfig config = new PhoneConfig();
				config.setPathToRead(props.get("phonefeed.read.dir.path").toString());
				config.setPathToWrite(props.get("phonefeed.write.dir.path").toString());
				config.setArchivePath(props.get("phonefeed.archive.path").toString());
				config.setFilePrefix(props.get("phonefeed.file.prefix").toString());
				config.setPhoneColumn(props.get("phonefeed.csv.phone.column").toString());
				config.setPhoneOptInStatusColumn(props.get("phonefeed.csv.phoneoptinstatus.column").toString());
				config.setTimeStampColumn(props.get("phonefeed.csv.timestamp.column").toString());
				config.setDateFormat(props.get("phonefeed.csv.dateformat").toString());
				config.setDelim(props.get("phonefeed.csv.delim").toString());
				config.setHeaderIncluded(Boolean.parseBoolean(props.get("phonefeed.csv.headerincluded").toString()));
				DAOManager daoManager = getDAOManager(propFile);
				setCustomerServicesApiServiceStub();
				PhoneProcessor processor = new PhoneProcessor();
				success = processor.process(config,daoManager);

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
	

	private static Map buildEnvironmentEntryMap(File tomcatContextFile)  {
		Map environmentMap = new HashMap();
		Document tomcatContextXml = null;
		try {
			tomcatContextXml = DomUtil.parse(tomcatContextFile);
		} catch (Exception e) {
			System.out.println("Failed to retrieve Environment Entries from the context file: " + e);
		}
		NodeList environmentEntries = tomcatContextXml.getElementsByTagName("Environment");
		for (int i = 0; i < environmentEntries.getLength(); i++) 
		{
			String environmentName = environmentEntries.item(i).getAttributes().getNamedItem("name").getNodeValue();
			String environmentValue = environmentEntries.item(i).getAttributes().getNamedItem("value").getNodeValue();
			environmentMap.put(environmentName, environmentValue);
		}
		NodeList resourceEntries = tomcatContextXml.getElementsByTagName("Resource");
		for (int i = 0; i < resourceEntries.getLength(); i++) 
		{
			if(resourceEntries.item(i).getAttributes().getNamedItem("name").getNodeValue().equalsIgnoreCase("jdbc/relateIntegrationDB"))
			{
				String driverClassName = resourceEntries.item(i).getAttributes().getNamedItem("driverClassName").getNodeValue();
				String url = resourceEntries.item(i).getAttributes().getNamedItem("url").getNodeValue();
				String username = resourceEntries.item(i).getAttributes().getNamedItem("username").getNodeValue();
				String password = resourceEntries.item(i).getAttributes().getNamedItem("password").getNodeValue();
				environmentMap.put("driverClassName", driverClassName);
				environmentMap.put("url", url);
				environmentMap.put("username", username);
				environmentMap.put("password", password);
				break;
			}
		}
		
		return environmentMap;
	}
	
	private static DAOManager getDAOManager(File tomcatContextFile)  
	{
		String driverClassName = props.get("driverClassName").toString();
		String url = props.get("url").toString();
		String username = props.get("username").toString();
		String password = props.get("password").toString();
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		
		PhoneSubscriptionDAOImpl phoneSubscriptionDAO = new PhoneSubscriptionDAOImpl();
		RIMUserDAOImpl rimUserDAO = new RIMUserDAOImpl();
		rimUserDAO.setDataSource(dataSource);
		phoneSubscriptionDAO.setDataSource(dataSource);
		
		DAOManager daoManager = new DAOManager();
		daoManager.setPhoneSubscriptionDAO(phoneSubscriptionDAO);
		daoManager.setRimUserDAO(rimUserDAO);
		return daoManager;	
	}
	
	private static void setCustomerServicesApiServiceStub() 
	{
		String certFileUrl =  props.get("cert.file.url").toString();
		System.setProperty("javax.net.ssl.trustStore",certFileUrl);
		
		String relateWsEndpointUrl =  props.get("relate.ws.endpoint.url").toString();
		try {
			CustomerWebServiceManager.relateWSStub = new CustomerServicesApiServiceStub(relateWsEndpointUrl);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
