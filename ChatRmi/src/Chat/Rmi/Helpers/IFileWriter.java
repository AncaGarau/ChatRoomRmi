package Chat.Rmi.Helpers;

public interface IFileWriter 
{
	public void CreateFileIfNotExists();
	public void WriteCredentials(String username,String password);
}
