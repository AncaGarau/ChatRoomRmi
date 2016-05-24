package Chat.Rmi.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Chat.Rmi.Models.LocalizedStrings;
import Chat.Rmi.Models.UsersFileException;


public class FileWriter
{
	File file = new File(LocalizedStrings.UsersFile);
	
	public void CreateFileIfNotExists() 
	{
		if(file.exists())
			return;
		
		try 
		{
			file.createNewFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void WriteCredentials(String username,String password) throws UsersFileException 
	{
		String credentials=username+","+password;
		
		try 
		{
			java.io.FileWriter fw=new java.io.FileWriter(file,true);
			fw.append(credentials);
			fw.append(System.getProperty("line.separator"));
			fw.close();
			
			System.out.println(LocalizedStrings.UserSaved);
		} 
		catch (FileNotFoundException e) 
		{
			throw new UsersFileException();
		} 
		catch (IOException e) 
		{
			throw new UsersFileException();
		}
	}
}
