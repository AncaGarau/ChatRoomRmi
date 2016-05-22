package Chat.Rmi.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import Chat.Rmi.Helpers.FileReader;

public class ServerLauncher 
{

	public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException 
	{
		FileReader fileReader = new FileReader();
		String serverAddress = fileReader.ReadServerAddress("C:\\Chat\\ServerAddress.config");
		
		Naming.rebind(serverAddress, new Server());
		System.out.println("Server is up and running...");
	}
}
