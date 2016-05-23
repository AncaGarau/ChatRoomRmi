package Chat.Rmi.Helpers;

import java.util.ArrayList;

import Chat.Rmi.Models.User;

public class UserValidator 
{
	
	public boolean UsernameIsUnique(String username, ArrayList<User> users)
	{
		for(User user : users)
			if(username.equals(user.getUsername()))
				return false;
		return true;
	}
	
	public boolean IsValidCredential(String credential)
	{
		
		if(credential == null)
			return false;
		
		if(credential.matches("^[a-zA-Z0-9]+$"))
			return true;
		
		return false;
	}
	
	public boolean UserExists(User user, ArrayList<User> users)
	{
		return users.contains(user);
	}
}
