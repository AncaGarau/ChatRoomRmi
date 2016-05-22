package Chat.Rmi.Helpers;

import java.util.List;

import Chat.Rmi.Models.ServerAddress;
import Chat.Rmi.Models.User;

public interface IFileReader 
{
	List<User> ReadExistingCredentials(String fileName);
	ServerAddress ReadServerAddress(String fileName);
}
