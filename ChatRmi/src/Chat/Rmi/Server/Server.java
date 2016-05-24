package Chat.Rmi.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Chat.Rmi.Client.IClient;
import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Helpers.FileWriter;
import Chat.Rmi.Helpers.UserValidator;
import Chat.Rmi.Models.LocalizedStrings;
import Chat.Rmi.Models.User;
import Chat.Rmi.Models.UsersFileException;

public class Server extends UnicastRemoteObject implements IServer 
{

	private static final long serialVersionUID = 1L;
	private ArrayList<IClient> clients;
	private UserValidator userValidator;
	private ArrayList<User> users;
	private FileWriter fileWriter;
		

	protected Server(UserValidator userValidator, FileReader fileReader, FileWriter fileWriter) throws RemoteException 
	{
		this.clients = new ArrayList<IClient>();
		this.userValidator = userValidator;
		this.users = (ArrayList<User>) fileReader.ReadExistingCredentials(LocalizedStrings.UsersFile);
		this.fileWriter = fileWriter;
	}

	@Override
	public void RegisterChatClient(IClient clientToRegister) throws RemoteException 
	{
		this.clients.add(clientToRegister);
		clientToRegister.GetUser().setToRegistered();
		this.users.add(clientToRegister.GetUser());
		this.saveUser(clientToRegister.GetUser());
		
		String msg = clientToRegister.GetUser().getUsername() + LocalizedStrings.HasSignedIn;
		this.BroadcastMessage(msg);
	}

	@Override
	public void LogInChatClient(IClient clientToLogIn) throws RemoteException 
	{
		this.clients.add(clientToLogIn);
		String msg = clientToLogIn.GetUser().getUsername() + LocalizedStrings.HasSignedIn;
		this.BroadcastMessage(msg);
	}

	@Override
	public void BroadcastMessage(String messageToBroadcast) throws RemoteException 
	{		
		messageToBroadcast = FormatEmoticons(messageToBroadcast);
					
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
		this.clients.remove(clientToLogOut);
	}
	
	public String FindCredentialsErrors(User user) throws RemoteException
	{
		String errors = "";
		
		if(user.isRegistered())
		{
			if(!userValidator.UserExists(user, users))
				return LocalizedStrings.InvalidCredentials;
		}
		else
		{
				if(!userValidator.IsValidCredential(user.getUsername()))
					errors = errors + LocalizedStrings.InvalidUserName;
				
				if(!userValidator.IsValidCredential(user.getPassword()))
					errors = errors + LocalizedStrings.InvalidPassword;
				
				if(!userValidator.UsernameIsUnique(user.getUsername(), users))
					errors = errors + LocalizedStrings.UserAlreadyExists;
		}
		
		return errors;
	}
	
	private void saveUser(User user)
	{
		this.fileWriter.CreateFileIfNotExists();
		try 
		{
			this.fileWriter.WriteCredentials(user.getUsername(), user.getPassword());
		} catch (UsersFileException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	private String FormatEmoticons(String messageToBroadcast) throws RemoteException
	{
		if(messageToBroadcast.contains(LocalizedStrings.DanceShortcut))
			messageToBroadcast = messageToBroadcast.replace(LocalizedStrings.DanceShortcut, LocalizedStrings.DanceEmoticon);
		if(messageToBroadcast.contains(LocalizedStrings.CatShortcut))
			messageToBroadcast = messageToBroadcast.replace(LocalizedStrings.CatShortcut, LocalizedStrings.CatEmoticon);
		if(messageToBroadcast.contains(LocalizedStrings.RockShortcut))
			messageToBroadcast = messageToBroadcast.replace(LocalizedStrings.RockShortcut, LocalizedStrings.RockEmoticon);
		return messageToBroadcast;
	}
}
