package Chat.Rmi.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import Chat.Rmi.Models.User;


public interface IClient extends Remote
{
	User getUser() throws Exception;
	void retrievMessage(String message)throws RemoteException;
}
