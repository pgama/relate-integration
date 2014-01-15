package com.rim.integration.io;

import java.io.File;
import java.io.FilenameFilter;

import com.rim.integration.config.ImpexConfig;


public class ImpexFileProcessor 
{
	public String[] getFilesToProcess(final ImpexConfig config)
	{
		String[] files = null;
		String sourcePath = config.getPathToRead();
		File sourceDir = new File(sourcePath);
		if(sourceDir.isDirectory())
		{
			files = sourceDir.list(new FilenameFilter() {
				
				public boolean accept(File file, String name) {
					
					if(name.startsWith(config.getFilePrefix()))
						return true;
					else
						return false;
				}
			});
		}
		return files;
	}
	
	public int archiveSourceFile(File processedFile, ImpexConfig config)
	{
		int success = 0;
		processedFile.renameTo(new File(config.getArchivePath()+File.separator+processedFile.getName()));
		return success;
	}
	public int deleteSourceFile(File processedFile)
	{
		int success = 0;
		processedFile.delete();
		return success;
	}
}
