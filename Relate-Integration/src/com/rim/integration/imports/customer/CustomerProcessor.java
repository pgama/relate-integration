package com.rim.integration.imports.customer;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.relateIntegration.dao.RIMUserDAO;
import com.relateIntegration.dao.support.DAOManager;
import com.relateIntegration.user.model.RIMUser;
import com.relateIntegration.util.CommerceUtil;
import com.rim.integration.imports.customer.config.CustomerConfig;
import com.rim.integration.imports.customer.csv.reader.CustomerCSVReader;
import com.rim.integration.imports.customer.xml.writer.CustomerXMLFileWriter;
import com.rim.integration.io.ImpexFileProcessor;



public class CustomerProcessor extends ImpexFileProcessor {

	public int process(CustomerConfig config,DAOManager daoManager )
	{
		int success = 0;
		String[] files = getFilesToProcess(config);
		if(files!=null && files.length>0)
		{
			for (int i=0;i<files.length;i++)
			{
				File file = new File(config.getPathToRead()+File.separator+files[i]);
				CustomerCSVReader reader = new CustomerCSVReader();
				List<RIMUser> customers = reader.readData(config,file);
				
				//Call Module code to update DB.
				List<RIMUser> customerListForXMLFile = updateDBAndGetCustomerListForXMLFile(customers,daoManager);
				
				CustomerXMLFileWriter writer = new CustomerXMLFileWriter();
				success = writer.writeData(config, customerListForXMLFile);
				archiveSourceFile(file, config);
			}
		}
		return success;
	}

	private List<RIMUser> updateDBAndGetCustomerListForXMLFile(List<RIMUser> customers,DAOManager daoManager )
	{		
		RIMUserDAO rimUserDAO = daoManager.getRimUserDAO();
		
		List<RIMUser> customerListForXMLFile = new ArrayList<RIMUser>();
		
		if(customers!=null && customers.iterator()!=null)
		{
			Iterator<RIMUser> iter= customers.iterator();
			while(iter.hasNext())
			{
				RIMUser temp = iter.next();
				boolean skipUser =false;
				RIMUser user = rimUserDAO.getRIMUserByEmail(temp.getEmail());
				if(user == null)
				{
					rimUserDAO.insertRIMUserWithEmail(temp.getEmail());
					user = rimUserDAO.getRIMUserByEmail(temp.getEmail());
				}
				else
				{
					if(user.getLastUpdatedTime()!=null && temp.getLastUpdatedTime()!=null && user.getLastUpdatedTime().compareTo(temp.getLastUpdatedTime()) > 0) //check if the user from csv has old timestamp
					{ skipUser = true; }
				}
				
				if(!skipUser)
				{
					Map<String, Object> attributes = CommerceUtil.getModuleTableDataMapForCustomerInformation(temp);
					rimUserDAO.updateRIMUserWithIntegrationId(attributes, user.getRelateIntegrationId());
					temp.setRelateIntegrationId(user.getRelateIntegrationId());
					customerListForXMLFile.add(temp);
				}
			}
			
		}
		
		return customerListForXMLFile;
		
	}
	
}
