package Chat.Rmi.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Chat.Rmi.Client.IClient;
import Chat.Rmi.Models.User;

public interface IServer extends Remote
{
	void RegisterChatClient(IClient clientToRegister) throws RemoteException;
	void LogInChatClient(IClient clientToLogIn) throws RemoteException;
	void BroadcastMessage(String messageToBroadcast) throws RemoteException;
	void LogOutChatClient(IClient clientToLogOut) throws RemoteException;
	String FindCredentialsErrors(User user) throws RemoteException;
}
