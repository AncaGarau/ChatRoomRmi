package Chat.Rmi.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Models.User;
import Chat.Rmi.Server.IServer;

public class ClientLauncher 
{
	public static void main(String[] args) throws Exception 
	{
		
		User user = GetClientCredentials();		
		IServer server = GetServerConnection();
		
		new Thread(new Client(user, server)).start();
	}
	
	private static User GetClientCredentials() throws Exception
	{
		int option = GetClientOption();
		switch(option)
		{
			case 1: return GetLogInCredentials();
			case 2: return GetRegistrationCredentials();
			default: throw new Exception("Invalid client option!");
		}
	}
	
	private static IServer GetServerConnection() throws MalformedURLException, RemoteException, NotBoundException
	{
		FileReader fileReader = new FileReader();
		
		String serverAddress = fileReader.ReadServerAddress("C:\\Chat\\ServerAddress.config");
		return (IServer)Naming.lookup(serverAddress);
	}
	
	private static int GetClientOption()
	{
		String welcomMessage="1.Log in\n2.Register\nEnter your option...\n";
 		System.out.println(welcomMessage);
 		
 		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		
 		while(option != 1 && option != 2)
 		{
 			System.out.println("Invalid option! Please try again: ");
 			option = scanner.nextInt();
 		}
 		
 		return option;
	}
	
	private static User GetLogInCredentials()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter username:");
		String username = scanner.nextLine();
		
		System.out.println("Enter password:");
		String password = scanner.nextLine();
				
		return new User(username, password);
	}
	
	private static User GetRegistrationCredentials()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter new user name:");
		String username = scanner.nextLine();
		
		System.out.println("Enter new password:");
		String password = scanner.nextLine();
		
		return new User(username, password);
	}

}