package com.rim.integration.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ImpexUtil 
{

	public static String roundUptoTwoDecimal(double value)
	{
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return twoDForm.format(value);
	}
	
	public static String getFormattedDate(String pattern, Date date)
	{
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static XMLGregorianCalendar convertToXMLDate(Date date)
	{
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar xmlDate = null;
		if(date!=null)
		{
			c.setTimeInMillis(date.getTime());
			try 
			{
				xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			}
			catch (DatatypeConfigurationException e) 
			{
				e.printStackTrace();
			}
		}
		return xmlDate;
		
	}
	
	public static XMLGregorianCalendar convertToXMLDate(String dateString,  String dateFormat)
	{
		DateFormat format = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date!=null ? convertToXMLDate(date) : null;
	}
	
	
	public static Date convertToJavaDate(XMLGregorianCalendar xmlDate)
	{
		return xmlDate!=null ? xmlDate.toGregorianCalendar().getTime() : null;		
	}
	
	public static String getDateSuffix()
	{
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("MMddyyyy_HHmmss");
		return format.format(date);
	}
	
}
