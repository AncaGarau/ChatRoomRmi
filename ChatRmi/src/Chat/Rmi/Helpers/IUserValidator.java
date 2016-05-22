package Chat.Rmi.Helpers;

import java.util.ArrayList;
import java.util.List;

import Chat.Rmi.Models.User;

public interface IUserValidator 
{
	boolean IsUsernameUnique(String username, List<User> users);
	boolean IsValidCredential(String credential);
	boolean UserExists(User user, ArrayList<User> users);
}
