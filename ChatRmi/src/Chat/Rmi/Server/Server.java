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
	}

	@Override
	public void LogInChatClient(IClient clientToLogIn) throws RemoteException 
	{
		this.clients.add(clientToLogIn);
	}

	@Override
	public void BroadcastMessage(String messageToBroadcast) throws RemoteException 
	{
		for(int i = 0; i < this.clients.size(); i++)
			this.clients.get(i).RetrieveMessage(messageToBroadcast);		
	}
}
