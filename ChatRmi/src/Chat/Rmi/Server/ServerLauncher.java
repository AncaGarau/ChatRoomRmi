package Chat.Rmi.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Models.ServerAddress;

public class ServerLauncher 
{

	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException 
	{
		FileReader fileReader = new FileReader();
		ServerAddress serverAddress = fileReader.ReadServerAddress("C:\\Chat\\ServerAddress.config");
		
		LocateRegistry.createRegistry(serverAddress.GetPort());
		Naming.rebind(GetServerUrl(serverAddress), new Server());
		System.out.println("Server is up and running...");
	}
	
	private static String GetServerUrl(ServerAddress serverAddress)
	{
		return "//" + serverAddress.GetIp() + ":" + serverAddress.GetPort() + "/" + serverAddress.GetName();
	}
}
