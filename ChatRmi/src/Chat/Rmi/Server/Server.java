package Chat.Rmi.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Chat.Rmi.Client.IClient;
import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Helpers.UserValidator;
import Chat.Rmi.Models.User;

public class Server extends UnicastRemoteObject implements IServer 
{

	private static final long serialVersionUID = 1L;
	private ArrayList<IClient> clients;
	private UserValidator userValidator;
	private ArrayList<User> users;
		

	protected Server(UserValidator userValidator, FileReader fileReader) throws RemoteException 
	{
		this.clients = new ArrayList<IClient>();
		this.userValidator = userValidator;
		this.users = (ArrayList<User>) fileReader.ReadExistingCredentials("C:\\Chat\\Users.txt");
		
	}

	@Override
	public void RegisterChatClient(IClient clientToRegister) throws RemoteException 
	{
		this.clients.add(clientToRegister);
		clientToRegister.GetUser().setToRegistered();
		this.users.add(clientToRegister.GetUser());
		
		String msg = clientToRegister.GetUser().getUsername() + " has signed in!";
		this.BroadcastMessage(msg);
	}

	@Override
	public void LogInChatClient(IClient clientToLogIn) throws RemoteException 
	{
		this.clients.add(clientToLogIn);
		String msg = clientToLogIn.GetUser().getUsername() + " has signed in!";
		this.BroadcastMessage(msg);
	}

	@Override
	public void BroadcastMessage(String messageToBroadcast) throws RemoteException 
	{		
		if(messageToBroadcast.contains("-dance"))
			messageToBroadcast = messageToBroadcast.replace("-dance", "\n\\('o')\n  ) )Z\n  /\\");
					
		for(int i = 0; i < this.clients.size(); i++)
		{
			try
			{
				this.clients.get(i).RetrieveMessage(messageToBroadcast);	
			}
			catch(Exception ex)
			{
				this.LogOutChatClient(this.clients.get(i));
				//String msg = this.clients.get(i).GetUser().getUsername() + " has signed out!";
				//this.BroadcastMessage(msg);
			}
		}
				
	}
	
	@Override
	public void LogOutChatClient(IClient clientToLogOut) throws RemoteException
	{
		this.clients.remove(clientToLogOut);
	}
	
	public String FindCredentialsErrors(User user) throws RemoteException
	{
		String errors = "";
		
		if(user.isRegistered())
		{
			if(!userValidator.UserExists(user, users))
				return "Invalid credentials!\n";
		}
		else
		{
				if(!userValidator.IsValidCredential(user.getUsername()))
					errors = errors + "Invalid user name!\n";
				
				if(!userValidator.IsValidCredential(user.getPassword()))
					errors = errors + "Invalid password!\n";
				
				if(!userValidator.UsernameIsUnique(user.getUsername(), users))
					errors = errors + "User name already exists!\n";
		}
		
		return errors;
	}
}
