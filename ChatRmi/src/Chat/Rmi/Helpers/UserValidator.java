package Chat.Rmi.Helpers;

import java.util.List;

import Chat.Rmi.Models.User;

public class UserValidator implements IUserValidator
{
	
	public boolean IsValidCredential(String credential)
	{
		
		if(credential == null)
			return false;
		
		if(credential.matches("^[a-zA-Z0-9]+$"))
			return true;
		
		return false;
	}

	@Override
	public boolean IsUsernameUnique(String username, List<User> users) 
	{
		for(User user : users)
			if(username == user.getUsername())
				return false;
		return true;
	}
}
