package Chat.Rmi.Helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Chat.Rmi.Models.User;

public class FileReader implements IFileReader
{
	@Override
	public List<User> ReadExistingCredentials(String fileName) 
	{
		List<User> users = new ArrayList<User>();
		try 
		{
			BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
			String line = br.readLine();
			while(line != null)
			{
				List<String> credentials = Arrays.asList(line.split(","));
				users.add(new User(credentials.get(0), credentials.get(1)));
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return users;
	}
	
	public String ReadServerAddress(String fileName)
	{
		String address = null;
		try 
		{
			BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
			address = br.readLine();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return address;
	}
}
