package Chat.Rmi.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Chat.Rmi.Client.IClient;
import Chat.Rmi.Helpers.FileReader;
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
	public void registerUser(IClient client) throws Exception 
	{
		if(!userValidator.IsValidCredential(client.getUser().getUsername()))
				throw new Exception("Username is not valid!");
		
		if(!userValidator.IsValidCredential(client.getUser().getPassword()))
			throw new Exception("Password is not valid!");
		
		if(!userValidator.IsUsernameUnique(client.getUser().getUsername(), users))
			throw new Exception("Username is not unique!");
		
		//register user if it is not registered
		if(!this.isUserRegistered(client.getUser()))
			users.add(client.getUser());
		
		onlineClients.add(client);
	}
	
	private boolean isUserRegistered(User user)
	{
		for(int i=0; i< users.size(); i++)
		{
			if(users.get(i).getUsername() == user.getUsername() &&
					users.get(i).getPassword() == user.getPassword())
				return true;
		}
		return false;
	}

	@Override
	public void broadcastMessage(String message) throws RemoteException 
	{
		for(int i=0; i < onlineClients.size(); i++)
		{
			onlineClients.get(i++).retrievMessage(message);
		}

	}
}
