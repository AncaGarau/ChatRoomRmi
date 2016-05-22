package Chat.Rmi.Helpers;

import java.util.List;

import Chat.Rmi.Models.User;

public interface IFileReader 
{
	public List<User> ReadCredentials(String fileName);
}
