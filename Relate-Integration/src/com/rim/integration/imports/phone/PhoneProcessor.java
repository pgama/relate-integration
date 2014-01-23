package com.rim.integration.imports.phone;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.relateIntegration.dao.support.DAOManager;
import com.relateIntegration.relate.webservice.commands.UpdatePhoneOptInStatusCommand;
import com.relateIntegration.user.model.PhoneSubscription;
import com.relateIntegration.util.CommerceUtil;
import com.rim.integration.imports.phone.config.PhoneConfig;
import com.rim.integration.imports.phone.csv.reader.PhoneCSVReader;
import com.rim.integration.io.ImpexFileProcessor;

public class PhoneProcessor extends ImpexFileProcessor {
	
	public int process(PhoneConfig config,DAOManager daoManager)
	{
		int success = 0;
		String[] files = getFilesToProcess(config);
		
		if(files!=null && files.length>0)
		{
			for (int i=0;i<files.length;i++)
			{
				File file = new File(config.getPathToRead()+File.separator+files[i]);
				PhoneCSVReader reader = new PhoneCSVReader();
				List<PhoneSubscription> phoneCustomers = reader.readData(config,file);
				
				//call WS commands
				if(phoneCustomers!=null && phoneCustomers.iterator()!=null)
				{
					Iterator<PhoneSubscription> iter = phoneCustomers.iterator(); 
					while(iter.hasNext())
					{
						PhoneSubscription user = iter.next();
						UpdatePhoneOptInStatusCommand.updatePhoneOptInStatus(CommerceUtil.getRelateRequestMapFromPhoneSubscription(user),daoManager);
					}
				}
				
				
			}
		}
		return success;
	}

}
