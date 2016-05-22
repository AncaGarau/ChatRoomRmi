package Chat.Rmi.Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Chat.Rmi.Models.User;
import Chat.Rmi.Server.IServer;

public class Launcher 
{
	private static String ServerUrl;
	

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException 
	{
		ServerUrl = "//localhost:1234/AaaChatServer";
		IServer server = (IServer)Naming.lookup(ServerUrl);
		new Thread(new Client(new User("aaa", "aaa"), server)).start();

	}

}
