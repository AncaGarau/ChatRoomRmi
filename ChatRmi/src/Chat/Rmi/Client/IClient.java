package Chat.Rmi.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote
{
	void RetrieveMessage(String messageToRetrieve) throws RemoteException;

}
