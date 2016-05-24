package Chat.Rmi.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Helpers.FileWriter;
import Chat.Rmi.Helpers.UserValidator;
import Chat.Rmi.Models.LocalizedStrings;
import Chat.Rmi.Models.ServerAddress;
import Chat.Rmi.Models.ServerAddressFileException;

public class ServerLauncher 
{

	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException 
	{
		FileReader fileReader = new FileReader();
		ServerAddress serverAddress;
		try 
		{
			serverAddress = fileReader.ReadServerAddress(LocalizedStrings.ServerAddressFile);
		} 
		catch (ServerAddressFileException e) 
		{
			e.printStackTrace();
			return;
		}
		
		LocateRegistry.createRegistry(serverAddress.GetPort());
		Naming.rebind(GetServerUrl(serverAddress), new Server(new UserValidator(), new FileReader(), new FileWriter()));
		System.out.println("Server is up and running...");
	}
	
	private static String GetServerUrl(ServerAddress serverAddress)
	{
		return "//" + serverAddress.GetIp() + ":" + serverAddress.GetPort() + "/" + serverAddress.GetName();
	}
}
