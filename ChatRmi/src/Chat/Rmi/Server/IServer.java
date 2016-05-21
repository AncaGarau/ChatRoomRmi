package Chat.Rmi.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import Chat.Rmi.Client.IClient;


public interface IServer extends Remote{
	void registerUser(IClient client) throws RemoteException, Exception;
	void broadcastMessage(String message) throws RemoteException;
}
