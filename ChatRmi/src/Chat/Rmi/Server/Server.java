package Chat.Rmi.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Chat.Rmi.Client.IClient;

public class Server extends UnicastRemoteObject implements IServer 
{

	private static final long serialVersionUID = 1L;
	private ArrayList<IClient> clients;
		

	protected Server() throws RemoteException 
	{
		clients = new ArrayList<IClient>();
	}

	@Override
	public void RegisterChatClient(IClient clientToRegister) throws RemoteException 
	{
		this.clients.add(clientToRegister);
		
		/*try
		{
			String msg = clientToRegister.GetUser().getUsername() + " has signed in!";
			this.BroadcastMessage(msg);
		}
		catch(RemoteException ex)
		{
			ex.printStackTrace();
		}*/

	}

	@Override
	public void LogInChatClient(IClient clientToLogIn) throws RemoteException 
	{
		this.clients.add(clientToLogIn);
		/*try
		{
			String msg = clientToLogIn.GetUser().getUsername() + " has signed in!";
			this.BroadcastMessage(msg);
		}
		catch(RemoteException ex)
		{
			ex.printStackTrace();
		}*/
	}

	@Override
	public void BroadcastMessage(String messageToBroadcast) throws RemoteException 
	{		
		for(int i = 0; i < this.clients.size(); i++)
		{
			try
			{
				this.clients.get(i).RetrieveMessage(messageToBroadcast);	
			}
			catch(Exception ex)
			{
				this.LogOutChatClient(this.clients.get(i));
			}
		}
				
	}
	
	@Override
	public void LogOutChatClient(IClient clientToLogOut) throws RemoteException
	{
		//System.out.println("Logged out chat client!");
		this.clients.remove(clientToLogOut);
	}
}
