package com.rim.integration.imports.common;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

public class ImpexCsvReader extends CsvReader 
{

	public ImpexCsvReader(String csvFilePath) throws FileNotFoundException 
	{
		super(csvFilePath);
	}

	public String get(String column) throws IOException 
	{
		try
		{
			int columnNumber = Integer.parseInt(column);
			return super.get(columnNumber-1);
		}
		catch(NumberFormatException ne)
		{
			return super.get(column);
		}
	}
}
