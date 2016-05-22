package Chat.Rmi.Server;

import java.awt.List;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Chat.Rmi.Client.IClient;
import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Helpers.IFileReader;
import Chat.Rmi.Helpers.IUserValidator;
import Chat.Rmi.Helpers.UserValidator;
import Chat.Rmi.Models.InvalidPasswordException;
import Chat.Rmi.Models.InvalidUserNameException;
import Chat.Rmi.Models.User;
import Chat.Rmi.Models.UserNameOrPasswordInvalidException;

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
		//if(!userValidator.UserExists(clientToLogIn.GetUser(), users))
			//throw new UserNameOrPasswordInvalidException();
		
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
			}
		}
				
	}
	
	@Override
	public void LogOutChatClient(IClient clientToLogOut) throws RemoteException
	{
		//System.out.println("Logged out chat client!");
		this.clients.remove(clientToLogOut);
	}
	
	public String FindCredentialsErrors(User user) throws RemoteException
	{
		String errors = "";
		
		if(!userValidator.IsValidCredential(user.getUsername()))
			errors += "Invalid user name!\n";
		
		if(!userValidator.IsValidCredential(user.getPassword()))
			errors += "Invalid password!\n";
		
		if(!userValidator.UsernameIsUnique(user.getUsername(), users))
			errors += "User name already exists!\n";
		
		return errors;
			
	}
}
