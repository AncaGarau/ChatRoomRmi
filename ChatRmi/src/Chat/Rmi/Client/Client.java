package Chat.Rmi.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Chat.Rmi.Models.User;
import Chat.Rmi.Server.IServer;

public class Client extends UnicastRemoteObject implements IClient
{
	private IServer server;
	private User user;
	
	protected Client(User user, IServer server) throws Exception {
		this.user = user;
		this.server = server;
		server.registerUser(this);
	}

	@Override
	public void retrievMessage(String message) throws RemoteException 
	{
		System.out.println(message);
	}

	@Override
	public User getUser()  throws Exception{
		if(user == null)
			throw new Exception("This client does not have a registered user!");
		return user;
	}
}
