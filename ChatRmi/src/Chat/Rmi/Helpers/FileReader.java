package Chat.Rmi.Helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Chat.Rmi.Models.ServerAddress;
import Chat.Rmi.Models.ServerAddressFileException;
import Chat.Rmi.Models.User;

public class FileReader
{
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
				users.add(new User(credentials.get(0), credentials.get(1), true));
				line = br.readLine();
			}
			br.close();
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
	
	public ServerAddress ReadServerAddress(String fileName) throws ServerAddressFileException
	{
		List<String> details = null;
		try 
		{
			BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
			String line = br.readLine();
			details = Arrays.asList(line.split(","));
			br.close();
		}
		catch (FileNotFoundException e) 
		{
			throw new ServerAddressFileException();
		} 
		catch (IOException e) 
		{
			throw new ServerAddressFileException();
		}
		return new ServerAddress(details.get(0), Integer.parseInt(details.get(1)), details.get(2));
	}
}
