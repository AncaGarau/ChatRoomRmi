package Chat.Rmi.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Launcher 
{

	public static void main(String[] args) throws RemoteException, MalformedURLException 
	{
		LocateRegistry.createRegistry(1234);
		Naming.rebind("//localhost:1234/AaaChatServer", new Server());
		System.out.println("Server is up and running...");
	}
}
