package Chat.Rmi.Helpers;

public interface IFileWriter 
{
	void CreateFileIfNotExists();
	void WriteCredentials(String username,String password);
}
