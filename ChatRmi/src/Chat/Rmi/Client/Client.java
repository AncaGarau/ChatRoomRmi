package Chat.Rmi.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import Chat.Rmi.Models.LocalizedStrings;
import Chat.Rmi.Models.User;
import Chat.Rmi.Server.IServer;

public class Client extends UnicastRemoteObject implements IClient, Runnable
{
	private static final long serialVersionUID = 1L;
	private User user;
	private IServer server;
	

	protected Client(User user, IServer server) throws RemoteException 
	{
		this.user = user;
		this.server = server;
		
		if(user.isRegistered())
			server.LogInChatClient(this);
		else
			server.RegisterChatClient(this);
	}
	
	@Override
	public User GetUser() throws RemoteException 
	{
		return this.user;
	}

	@Override
	public void RetrieveMessage(String messageToRetrieve) throws RemoteException 
	{
		System.out.println(messageToRetrieve);
	}

	@Override
	public void run() 
	{
		Scanner scanner = new Scanner(System.in);
		String message;
		
		System.out.println(LocalizedStrings.WelcomeToChat);
		
		while(true)
		{
			message = scanner.nextLine();
			try
			{
				this.server.BroadcastMessage(user.getUsername() + ":" + message);
			}
			catch(RemoteException ex)
			{
				System.out.println(LocalizedStrings.ProblemCommunicating);
			}
		}
	}
}
