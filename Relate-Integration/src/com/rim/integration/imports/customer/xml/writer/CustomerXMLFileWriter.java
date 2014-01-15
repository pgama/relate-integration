package com.rim.integration.imports.customer.xml.writer;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.relateIntegration.user.model.RIMUser;
import com.rim.integration.imports.customer.config.CustomerConfig;
import com.rim.integration.utils.ImpexUtil;
import com.rim.integration.xml.customer.AddressType;
import com.rim.integration.xml.customer.AlternateKeyType;
import com.rim.integration.xml.customer.ContactInformationType;
import com.rim.integration.xml.customer.ContactPreferenceContactType;
import com.rim.integration.xml.customer.ContactPreferenceType;
import com.rim.integration.xml.customer.Customer;
import com.rim.integration.xml.customer.CustomerNameType;
import com.rim.integration.xml.customer.CustomerNameTypeEntry;
import com.rim.integration.xml.customer.Customers;
import com.rim.integration.xml.customer.EMailType;
import com.rim.integration.xml.customer.EntityInformationType;
import com.rim.integration.xml.customer.IndividualType;
import com.rim.integration.xml.customer.NameLocationType;
import com.rim.integration.xml.customer.ObjectFactory;
import com.rim.integration.xml.customer.PersonalPreferencesType;
import com.rim.integration.xml.customer.TelephoneType;


public class CustomerXMLFileWriter {

	public int writeData(CustomerConfig config,List<RIMUser> customers)
	{
		int success = 0;
		System.out.println(customers.size());

		try
		{
			JAXBContext customersJAXBC = JAXBContext.newInstance(Customers.class.getPackage().getName());
			Marshaller customersMarshaller = customersJAXBC.createMarshaller();
			ObjectFactory customersObjectFactory = new ObjectFactory();
			Customers customersXML = customersObjectFactory.createCustomers();
				
			List<Customer> customerList = customersXML.getCustomer();
			if(customers!=null && customers.size()>0)
			{
				for(int i=0;i<customers.size();i++)
				{
					RIMUser customer = (RIMUser)customers.get(i);
					if(customer!=null)
					{
						Customer customerRec = new Customer();
						customerRec.setAction("AddorUpdate");
						
						EntityInformationType entityInformation = new EntityInformationType();
						IndividualType individual = new IndividualType();
		
						//Name
						CustomerNameType mainName = new CustomerNameType();
						if(customer.getLastName()!=null && !customer.getLastName().isEmpty() && !customer.getLastName().equalsIgnoreCase("null"))
						{
							CustomerNameTypeEntry lName = new CustomerNameTypeEntry();
							lName.setLocation(NameLocationType.LAST);
							lName.setValue(customer.getLastName());
							mainName.getName().add(lName);
						}
						if(customer.getFirstName()!=null && !customer.getFirstName().isEmpty() && !customer.getFirstName().equalsIgnoreCase("null"))
						{
							CustomerNameTypeEntry fName = new CustomerNameTypeEntry();
							fName.setLocation(NameLocationType.FIRST);
							fName.setValue(customer.getFirstName());
							mainName.getName().add(fName);	
						}
						individual.setName(mainName);
						
						ContactInformationType contactInformation = new ContactInformationType();	
						
						//Email
						if(customer.getEmail()!=null && !customer.getEmail().isEmpty() && !customer.getEmail().equalsIgnoreCase("null"))
						{
							EMailType email = new EMailType();
							email.setEMailAddress(customer.getEmail());
							email.setPrimaryFlag(true);
							contactInformation.getEMail().add(email);
						}
						
						//Phone
						if(customer.getPhoneNumber()!=null && !customer.getPhoneNumber().isEmpty() && !customer.getPhoneNumber().equalsIgnoreCase("null"))
						{
							TelephoneType phone = new TelephoneType();
							phone.setPhoneNumber(customer.getPhoneNumber());
							phone.setPrimaryFlag(true);
							contactInformation.getTelephone().add(phone);
						}
						
						//Address
						AddressType address = new AddressType();
						address.setPrimaryFlag(true);
						if(customer.getAddressLine1()!=null && !customer.getAddressLine1().isEmpty() && !customer.getAddressLine1().equalsIgnoreCase("null"))
							address.setAddressLine1(customer.getAddressLine1());
						if(customer.getAddressLine2()!=null && !customer.getAddressLine2().isEmpty() && !customer.getAddressLine2().equalsIgnoreCase("null"))
							address.setAddressLine2(customer.getAddressLine2());
						if(customer.getCity()!=null && !customer.getCity().isEmpty() && !customer.getCity().equalsIgnoreCase("null"))	
							address.setCity(customer.getCity());
						if(customer.getState()!=null && !customer.getState().isEmpty() && !customer.getState().equalsIgnoreCase("null"))	
							address.setTerritory(customer.getState());
						if(customer.getCountry()!=null && !customer.getCountry().isEmpty() && !customer.getCountry().equalsIgnoreCase("null"))
							address.setCountry(customer.getCountry());
						if(customer.getZipCode()!=null && !customer.getZipCode().isEmpty() && !customer.getZipCode().equalsIgnoreCase("null"))
							address.setPostalCode(customer.getZipCode());
						contactInformation.getAddress().add(address);
						
						
						individual.setContactInformation(contactInformation);
						entityInformation.setIndividual(individual);
						customerRec.setEntityInformation(entityInformation);
						
						//AlternateKey
						AlternateKeyType altKey = new AlternateKeyType();
						if(customer.getRelateIntegrationId() > 0 )
						{	
							altKey.setAlternateID(String.valueOf(customer.getRelateIntegrationId())); 
							altKey.setTypeCode("RELATEMODULEID");
							customerRec.getAlternateKey().add(altKey );
						}
						
						//Preferences
						if(customer.getEmailOptInStatus()!=null && !customer.getEmailOptInStatus().isEmpty() && !customer.getEmailOptInStatus().equalsIgnoreCase("null"))
						{
							PersonalPreferencesType preferences = new PersonalPreferencesType();
							ContactPreferenceType emailPref = new ContactPreferenceType();
							emailPref.setContactType(ContactPreferenceContactType.EMAIL);
							emailPref.setPermission(Boolean.parseBoolean(customer.getEmailOptInStatus()));
							preferences.getContactPreference().add(emailPref);
							customerRec.setPersonalPreferences(preferences);
						}
						
						customerList.add(customerRec);
					}
				}
			}
			File file = new File(config.getPathToWrite()+File.separator+config.getFilePrefix()+ImpexUtil.getDateSuffix()+".xml");
			customersMarshaller.marshal(customersXML, file);
		}
		catch(JAXBException je)
		{
			success = 1;
			je.printStackTrace();
		}
		return success;
	}

}
