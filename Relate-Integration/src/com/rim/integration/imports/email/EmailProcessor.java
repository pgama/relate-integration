package com.rim.integration.imports.email;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.relateIntegration.dao.support.DAOManager;
import com.relateIntegration.relate.webservice.commands.AddOrUpdateCustomerCommand;
import com.relateIntegration.user.model.RIMUser;
import com.relateIntegration.util.CommerceUtil;
import com.rim.integration.imports.email.config.EmailConfig;
import com.rim.integration.imports.email.csv.reader.EmailCSVReader;
import com.rim.integration.io.ImpexFileProcessor;

public class EmailProcessor extends ImpexFileProcessor {
	
	public int process(EmailConfig config,DAOManager daoManager)
	{
		int success = 0;
		String[] files = getFilesToProcess(config);
		
		if(files!=null && files.length>0)
		{
			for (int i=0;i<files.length;i++)
			{
				File file = new File(config.getPathToRead()+File.separator+files[i]);
				EmailCSVReader reader = new EmailCSVReader();
				List<RIMUser> emailCustomers = reader.readData(config,file);
				
				//call WS commands
				if(emailCustomers!=null && emailCustomers.iterator()!=null)
				{
					Iterator<RIMUser> iter = emailCustomers.iterator(); 
					while(iter.hasNext())
					{
						RIMUser user = iter.next();
						AddOrUpdateCustomerCommand.addOrUpdateCustomerService(CommerceUtil.getRelateRequestMapFromRimUser(user),daoManager);
					}
				}
				
			}
		}
		return success;
	}

}
