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
	public List<User> ReadCredentials(String fileName) 
	{
		List<User> users = new ArrayList<User>();
		try {
				@SuppressWarnings("resource")
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		    
		    	
		
	}	
}
