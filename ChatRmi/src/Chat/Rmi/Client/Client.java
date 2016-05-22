package Chat.Rmi.Client;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

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
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String message;
		
		System.out.println("\n\nWelcome to the chat!\n\n");
		
		while(true)
		{
			message = scanner.nextLine();
			try
			{
				this.server.BroadcastMessage(user.getUsername() + ":" + message);
			}
			catch(RemoteException ex)
			{
				System.out.println("Crashed in client");
				ex.printStackTrace();
			}

		}
		
	}
}
