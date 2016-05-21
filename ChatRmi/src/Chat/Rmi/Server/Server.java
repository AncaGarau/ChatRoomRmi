package Chat.Rmi.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Chat.Rmi.Client.IClient;
import Chat.Rmi.Helpers.IFileReader;
import Chat.Rmi.Helpers.IFileWriter;
import Chat.Rmi.Helpers.IUserValidator;
import Chat.Rmi.Models.User;

public class Server extends UnicastRemoteObject implements IServer 
{

	private List<User> users;
	private List<IClient> onlineClients;
	private IFileWriter fileWriter;
	private IUserValidator userValidator;

	protected Server(IFileReader fileReader, IFileWriter fileWriter, IUserValidator userValidator) throws RemoteException 
	{
		users = fileReader.ReadCredentials("credentials.txt");
		
		this.fileWriter = fileWriter;
		this.userValidator = userValidator;
	}

	@Override
	public void registerUser(User user) throws Exception 
	{
		if(!userValidator.IsValidCredential(user.getUsername()))
				throw new Exception("Username is not valid!");
		
		if(!userValidator.IsValidCredential(user.getPassword()))
			throw new Exception("Password is not valid!");
		
		if(!userValidator.IsUsernameUnique(user.getUsername(), users))
			throw new Exception("Username is not unique!");
		
		users.add(user);
	}

	@Override
	public void broadcastMessage(String message) throws RemoteException 
	{
		// TODO Auto-generated method stub

	}
}
