package Chat.Rmi.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Chat.Rmi.Models.User;

public interface IServer extends Remote{
	void registerUser(User user) throws RemoteException, Exception;
	void broadcastMessage(String message) throws RemoteException;
}
