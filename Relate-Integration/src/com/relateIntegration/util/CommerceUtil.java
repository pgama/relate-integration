package com.relateIntegration.util;

import java.util.HashMap;
import java.util.Map;

import com.relateIntegration.user.model.PhoneSubscription;
import com.relateIntegration.user.model.RIMUser;

public class CommerceUtil {
	
	public static Map<String, Object> getModuleTableDataMapForCustomerInformation (Map<String , Object> attributes)
	{
		Map<String , Object> relateMappingAtrributes =new HashMap<String, Object>();
		
		if(attributes.containsKey(RIMConstants.RequestMapping.EMAIL_KEY) && !attributes.get(RIMConstants.RequestMapping.EMAIL_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.EMAIL_KEY, attributes.get(RIMConstants.RequestMapping.EMAIL_KEY));
		
		if(attributes.containsKey(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE_OCPID) && !attributes.get(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE_OCPID).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.OCP_CUST_ID_KEY, attributes.get(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE_OCPID));
		
		if(attributes.containsKey(RIMConstants.RequestMapping.FIRST_NAME_KEY) && !attributes.get(RIMConstants.RequestMapping.FIRST_NAME_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.FIRST_NAME_KEY, attributes.get(RIMConstants.RequestMapping.FIRST_NAME_KEY));
		
		if(attributes.containsKey(RIMConstants.RequestMapping.LAST_NAME_KEY) && !attributes.get(RIMConstants.RequestMapping.LAST_NAME_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.LAST_NAME_KEY, attributes.get(RIMConstants.RequestMapping.LAST_NAME_KEY));
		
		if(attributes.containsKey(RIMConstants.RequestMapping.ADDRESSLINE1_KEY) && !attributes.get(RIMConstants.RequestMapping.ADDRESSLINE1_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.ADDRESS_LINE_1_KEY, attributes.get(RIMConstants.RequestMapping.ADDRESSLINE1_KEY));
			
		if(attributes.containsKey(RIMConstants.RequestMapping.ADDRESSLINE2_KEY) && !attributes.get(RIMConstants.RequestMapping.ADDRESSLINE2_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.ADDRESS_LINE_2_KEY, attributes.get(RIMConstants.RequestMapping.ADDRESSLINE2_KEY));
			
		if(attributes.containsKey(RIMConstants.RequestMapping.CITY_KEY) && !attributes.get(RIMConstants.RequestMapping.CITY_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.CITY_KEY, attributes.get(RIMConstants.RequestMapping.CITY_KEY));
				
		if(attributes.containsKey(RIMConstants.RequestMapping.TERRITORY_KEY) && !attributes.get(RIMConstants.RequestMapping.TERRITORY_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.STATE_KEY, attributes.get(RIMConstants.RequestMapping.TERRITORY_KEY));
		
		if(attributes.containsKey(RIMConstants.RequestMapping.COUNTRY_KEY) && !attributes.get(RIMConstants.RequestMapping.COUNTRY_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.COUNTRY_KEY, attributes.get(RIMConstants.RequestMapping.COUNTRY_KEY));
			
		if(attributes.containsKey(RIMConstants.RequestMapping.POSTAL_CODE_KEY) && !attributes.get(RIMConstants.RequestMapping.POSTAL_CODE_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.ZIP_CODE_KEY, attributes.get(RIMConstants.RequestMapping.POSTAL_CODE_KEY));
			
		if(attributes.containsKey(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY) && !attributes.get(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY).toString().isEmpty())
		{
			if(attributes.get(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY).toString().equalsIgnoreCase("true"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.EMAIL_OPT_IN_STATUS_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.EMAIL_OPT_IN_STATUS_KEY, "N");
		}

		if(attributes.containsKey(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY) && !attributes.get(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY).toString().isEmpty())
		{
			if(attributes.get(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY).toString().equalsIgnoreCase("true"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_ACCESSORIES_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_ACCESSORIES_KEY, "N");
		}
		
		if(attributes.containsKey(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY) && !attributes.get(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY).toString().isEmpty())
		{
			if(attributes.get(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY).toString().equalsIgnoreCase("true"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_CLOTHES_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_CLOTHES_KEY, "N");
		}
		if(attributes.containsKey(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY) && !attributes.get(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY).toString().isEmpty())
		{
			if(attributes.get(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY).toString().equalsIgnoreCase("true"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_SHOES_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_SHOES_KEY, "N");
		}
		if(attributes.containsKey(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY) && !attributes.get(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY).toString().isEmpty())
		{
			if(attributes.get(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY).toString().equalsIgnoreCase("true"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_FASHION_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_FASHION_KEY, "N");
		}

		if(attributes.containsKey(RIMConstants.RequestMapping.PHONE_NUMBER_KEY) && !attributes.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY).toString().isEmpty())
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.PHONE_NUMBER_KEY, attributes.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY));
		
		return relateMappingAtrributes;
	}
	
	public static Map<String, Object> getModuleTableDataMapForCustomerInformation (RIMUser user)
	{
		Map<String , Object> relateMappingAtrributes =new HashMap<String, Object>();
		
		if(user.getEmail()!=null && !user.getEmail().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.EMAIL_KEY, user.getEmail());
		
		if(user.getOcpCustId()!=null && !user.getOcpCustId().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.OCP_CUST_ID_KEY, user.getOcpCustId());
		
		if(user.getFirstName()!=null  && !user.getFirstName().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.FIRST_NAME_KEY, user.getFirstName());
		
		if(user.getLastName()!=null  && !user.getLastName().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.LAST_NAME_KEY, user.getLastName());
		
		if(user.getAddressLine1()!=null  && !user.getAddressLine1().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.ADDRESS_LINE_1_KEY, user.getAddressLine1());
			
		if(user.getAddressLine2()!=null && !user.getAddressLine2().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.ADDRESS_LINE_2_KEY, user.getAddressLine2());
			
		if(user.getCity()!=null && !user.getCity().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.CITY_KEY, user.getCity());
				
		if(user.getState()!=null && !user.getState().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.STATE_KEY, user.getState());
		
		if(user.getCountry()!=null && !user.getCountry().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.COUNTRY_KEY, user.getCountry());
			
		if(user.getZipCode()!=null && !user.getZipCode().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.ZIP_CODE_KEY, user.getZipCode());
			
		if(user.getEmailOptInStatus()!=null && !user.getEmailOptInStatus().equalsIgnoreCase("null"))
		{
			if(user.getEmailOptInStatus().equalsIgnoreCase("Y"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.EMAIL_OPT_IN_STATUS_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.EMAIL_OPT_IN_STATUS_KEY, "N");
		}
		
		if(user.getObsession_accessories()!=null && !user.getObsession_accessories().equalsIgnoreCase("null"))
		{
			if(user.getObsession_accessories().equalsIgnoreCase("Y"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_ACCESSORIES_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_ACCESSORIES_KEY, "N");
		}
		
		if(user.getObsession_clothes()!=null && !user.getObsession_clothes().equalsIgnoreCase("null"))
		{
			if(user.getObsession_clothes().equalsIgnoreCase("Y"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_CLOTHES_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_CLOTHES_KEY, "N");
		}
		
		if(user.getObsession_shoes()!=null && !user.getObsession_shoes().equalsIgnoreCase("null"))
		{
			if(user.getObsession_shoes().equalsIgnoreCase("Y"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_SHOES_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_SHOES_KEY, "N");
		}
		
		if(user.getObsession_fashion()!=null && !user.getObsession_fashion().equalsIgnoreCase("null"))
		{
			if(user.getObsession_fashion().equalsIgnoreCase("Y"))
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_FASHION_KEY, "Y");
			else
				relateMappingAtrributes.put(RIMConstants.CustomerInformation.OBSESSION_FASHION_KEY, "N");
		}	
		
		if(user.getPhoneNumber()!=null && !user.getPhoneNumber().equalsIgnoreCase("null"))
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.PHONE_NUMBER_KEY, user.getPhoneNumber());
		
		if(user.getLastUpdatedTime()!=null)
			relateMappingAtrributes.put(RIMConstants.CustomerInformation.LAST_UPDATED_TIME_KEY, user.getLastUpdatedTime());
		
		return relateMappingAtrributes;
	}
	
	public static Map<String, Object> getModuleTableDataMapForPhoneSubscription (Map<String , Object> attributes)
	{
		Map<String , Object> relateMappingAtrributes =new HashMap<String, Object>();
		
		if(attributes.containsKey(RIMConstants.RequestMapping.PHONE_NUMBER_KEY))
			relateMappingAtrributes.put(RIMConstants.PhoneSubscription.PHONE_NUMBER_KEY, attributes.get(RIMConstants.RequestMapping.PHONE_NUMBER_KEY));
		
		if(attributes.containsKey(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY).toString().equalsIgnoreCase("true"))
					relateMappingAtrributes.put(RIMConstants.PhoneSubscription.PHONE_OPT_IN_STATUS_KEY, "Y");
				else
					relateMappingAtrributes.put(RIMConstants.PhoneSubscription.PHONE_OPT_IN_STATUS_KEY, "N");
			}
		
		return relateMappingAtrributes;
	}
	
	
	public static RIMUser getRIMUserFromMap(Map<String , Object> attributes)
	{
			RIMUser user =new RIMUser();
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.EMAIL_KEY))
				user.setEmail(attributes.get(RIMConstants.CustomerInformation.EMAIL_KEY).toString());
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.OCP_CUST_ID_KEY))
				user.setOcpCustId(attributes.get(RIMConstants.CustomerInformation.OCP_CUST_ID_KEY).toString());
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.RELATE_INTEGRATION_ID_KEY))
				user.setRelateIntegrationId(Integer.parseInt(attributes.get(RIMConstants.CustomerInformation.RELATE_INTEGRATION_ID_KEY).toString()));
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.ADDRESS_LINE_1_KEY))
				user.setAddressLine1(attributes.get(RIMConstants.CustomerInformation.ADDRESS_LINE_1_KEY).toString());
				
			if(attributes.containsKey(RIMConstants.CustomerInformation.ADDRESS_LINE_2_KEY))
				user.setAddressLine2(attributes.get(RIMConstants.CustomerInformation.ADDRESS_LINE_2_KEY).toString());
				
			if(attributes.containsKey(RIMConstants.CustomerInformation.CITY_KEY))
				user.setCity(attributes.get(RIMConstants.CustomerInformation.CITY_KEY).toString());
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.STATE_KEY))
				user.setState(attributes.get(RIMConstants.CustomerInformation.STATE_KEY).toString());
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.COUNTRY_KEY))
				user.setCountry(attributes.get(RIMConstants.CustomerInformation.COUNTRY_KEY).toString());
				
			if(attributes.containsKey(RIMConstants.CustomerInformation.ZIP_CODE_KEY))
				user.setZipCode(attributes.get(RIMConstants.CustomerInformation.ZIP_CODE_KEY).toString());
				
			if(attributes.containsKey(RIMConstants.CustomerInformation.EMAIL_OPT_IN_STATUS_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY).toString().equalsIgnoreCase("true"))
					user.setEmailOptInStatus("Y");
				else
					user.setEmailOptInStatus("N");
			}
			if(attributes.containsKey(RIMConstants.CustomerInformation.OBSESSION_ACCESSORIES_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY).toString().equalsIgnoreCase("true"))
					user.setObsession_accessories("Y");
				else
					user.setObsession_accessories("N");
			}
			if(attributes.containsKey(RIMConstants.CustomerInformation.OBSESSION_CLOTHES_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY).toString().equalsIgnoreCase("true"))
					user.setObsession_clothes("Y");
				else
					user.setObsession_clothes("N");
			}
			if(attributes.containsKey(RIMConstants.CustomerInformation.OBSESSION_SHOES_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY).toString().equalsIgnoreCase("true"))
					user.setObsession_shoes("Y");
				else
					user.setObsession_shoes("N");
			}
			if(attributes.containsKey(RIMConstants.CustomerInformation.OBSESSION_FASHION_KEY))
			{
				if(attributes.get(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY).toString().equalsIgnoreCase("true"))
					user.setObsession_fashion("Y");
				else
					user.setObsession_fashion("N");
			}
	
			if(attributes.containsKey(RIMConstants.CustomerInformation.PHONE_NUMBER_KEY))
				user.setPhoneNumber(attributes.get(RIMConstants.CustomerInformation.PHONE_NUMBER_KEY).toString());
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.FIRST_NAME_KEY))
				user.setFirstName(attributes.get(RIMConstants.CustomerInformation.FIRST_NAME_KEY).toString());
			
			if(attributes.containsKey(RIMConstants.CustomerInformation.LAST_NAME_KEY))
				user.setLastName(attributes.get(RIMConstants.CustomerInformation.LAST_NAME_KEY).toString());
			
			return user;
			
	}

	public static Map<String, Object> getRelateRequestMapFromRimUser(RIMUser user)
	{
		Map<String , Object> requestMap =new HashMap<String, Object>();
		if(user!=null)
		{
			if(user.getFirstName()!=null)
				requestMap.put(RIMConstants.RequestMapping.FIRST_NAME_KEY, user.getFirstName());
			
			if(user.getLastName()!=null)
				requestMap.put(RIMConstants.RequestMapping.LAST_NAME_KEY, user.getLastName());
			
			if(user.getEmail()!=null)
				requestMap.put(RIMConstants.RequestMapping.EMAIL_KEY, user.getEmail());
			
			if(user.getAddressLine1()!=null)
				requestMap.put(RIMConstants.RequestMapping.ADDRESSLINE1_KEY, user.getAddressLine1());
			
			if(user.getAddressLine2()!=null)
				requestMap.put(RIMConstants.RequestMapping.ADDRESSLINE2_KEY, user.getAddressLine2());
			
			if(user.getCity()!=null)
				requestMap.put(RIMConstants.RequestMapping.CITY_KEY, user.getCity());
			
			if(user.getState()!=null)
				requestMap.put(RIMConstants.RequestMapping.TERRITORY_KEY, user.getState());
			
			if(user.getCountry()!=null)
				requestMap.put(RIMConstants.RequestMapping.COUNTRY_KEY, user.getCountry());
			
			if(user.getZipCode()!=null)
				requestMap.put(RIMConstants.RequestMapping.POSTAL_CODE_KEY, user.getZipCode());
			
			if(user.getEmailOptInStatus()!=null)
				requestMap.put(RIMConstants.RequestMapping.EMAIL_OPT_IN_KEY, user.getEmailOptInStatus());
			
			if(user.getObsession_accessories()!=null)
				requestMap.put(RIMConstants.RequestMapping.OBSESSION_ACCESSORIES_KEY, user.getObsession_accessories());
			
			if(user.getObsession_clothes()!=null)
				requestMap.put(RIMConstants.RequestMapping.OBSESSION_CLOTHES_KEY, user.getObsession_clothes());
			
			if(user.getObsession_shoes()!=null)
				requestMap.put(RIMConstants.RequestMapping.OBSESSION_SHOES_KEY, user.getObsession_shoes());
			
			if(user.getObsession_fashion()!=null)
				requestMap.put(RIMConstants.RequestMapping.OBSESSION_FASHION_KEY, user.getObsession_fashion());
			
			if(user.getRelateIntegrationId()!= 0)
				requestMap.put(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE_MODULEID, user.getRelateIntegrationId());
			
			if(user.getOcpCustId()!= null)
				requestMap.put(RIMConstants.RequestMapping.AlTERNATE_KEY_TYPECODE_OCPID, user.getOcpCustId());
			
			if(user.getPhoneNumber()!= null)
				requestMap.put(RIMConstants.RequestMapping.PHONE_NUMBER_KEY, user.getPhoneNumber());

			if(user.getLastUpdatedTime()!=null)
				requestMap.put(RIMConstants.RequestMapping.TIMESTAMP_KEY, user.getLastUpdatedTime());
		}
		
		return requestMap;
		
	}
	
	public static Map<String, Object> getRelateRequestMapFromPhoneSubscription(PhoneSubscription phoneUser)
	{
		Map<String , Object> requestMap =new HashMap<String, Object>();
		
		if(phoneUser !=null)
		{
			if(phoneUser.getPhoneNumber()!=0)
				requestMap.put(RIMConstants.RequestMapping.PHONE_NUMBER_KEY, phoneUser.getPhoneNumber());
			
			if(phoneUser.getPhoneOptInStatus()!=null)
				requestMap.put(RIMConstants.RequestMapping.PHONE_OPT_IN_KEY, phoneUser.getPhoneOptInStatus());
			
			if(phoneUser.getLastUpdatedTime()!=null)
				requestMap.put(RIMConstants.RequestMapping.TIMESTAMP_KEY, phoneUser.getLastUpdatedTime());
		}
		
		return requestMap;
		
	}
}
