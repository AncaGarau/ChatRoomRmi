package Chat.Rmi.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Chat.Rmi.Models.User;

public interface IClient extends Remote
{
	void RetrieveMessage(String messageToRetrieve) throws RemoteException;
	User GetUser() throws RemoteException;
}
