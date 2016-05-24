package Chat.Rmi.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Models.InvalidClientOptionException;
import Chat.Rmi.Models.LocalizedStrings;
import Chat.Rmi.Models.ServerAddress;
import Chat.Rmi.Models.ServerAddressFileException;
import Chat.Rmi.Models.User;
import Chat.Rmi.Server.IServer;

public class ClientLauncher 
{
	public static void main(String[] args) throws Exception 
	{
		IServer server;
		
		try
		{
			server = GetServer();	
		}
		catch(Exception ex)
		{
			System.out.println(LocalizedStrings.ErrorTryingToConnect);
			return;
		}
				
		User user = GetClientCredentials();	
		String errors = server.FindCredentialsErrors(user);
		
		while(!errors.equals(""))
		{
			System.out.println(errors);
			user = GetClientCredentials();	
			errors = server.FindCredentialsErrors(user);
		}
		
		new Thread(new Client(user, server)).start();		
		
	}
	
	private static User GetClientCredentials() throws InvalidClientOptionException
	{
		int option = GetClientOption();
		switch(option)
		{
			case 1: return GetLogInCredentials();
			case 2: return GetRegistrationCredentials();
			default: throw new InvalidClientOptionException();
		}
	}
	
	private static IServer GetServer() throws MalformedURLException, RemoteException, NotBoundException, ServerAddressFileException
	{
		FileReader fileReader = new FileReader();
		
		ServerAddress serverAddress = fileReader.ReadServerAddress(LocalizedStrings.ServerAddressFile);
		return (IServer)Naming.lookup("//" + serverAddress.GetIp() + ":" + serverAddress.GetPort() + "/" + serverAddress.GetName());
	}
	
	private static int GetClientOption()
	{
 		System.out.println(LocalizedStrings.Menu);
 		
 		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		
 		while(option != 1 && option != 2)
 		{
 			System.out.println(LocalizedStrings.InvalidOption);
 			option = scanner.nextInt();
 		}
 		return option;
	}
	
	private static User GetLogInCredentials()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(LocalizedStrings.EnterUser);
		String username = scanner.nextLine();
		
		System.out.println(LocalizedStrings.EnterPassword);
		String password = scanner.nextLine();
				
		return new User(username, password, true);
	}
	
	private static User GetRegistrationCredentials()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(LocalizedStrings.EnterNewUser);
		String username = scanner.nextLine();
		
		System.out.println(LocalizedStrings.EnterNewPassword);
		String password = scanner.nextLine();
		
		return new User(username, password, false);
	}

}
