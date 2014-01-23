package com.relateIntegration.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class DomUtil 
{

	protected static DocumentBuilderFactory dbFactory = null;
	
	public static Document parse(File file)throws SAXException, IOException, ParserConfigurationException {

		return getDocumentBuilder().parse(file);

	}

	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException 
	{
		DocumentBuilder db = null;
		
		// Get an instance of a DocumentBuilderFactory if we haven't already
		if (dbFactory == null) {
			dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
		}
		
		// Make sure we don't allow more than one thread to touch
		// the DocumentBuilderFactory concurrently since the underlying
		// implementation is not guaranteed to be thread safe
		synchronized (dbFactory) {
		
			db = dbFactory.newDocumentBuilder();
		
		}
		return db;
	}
}
