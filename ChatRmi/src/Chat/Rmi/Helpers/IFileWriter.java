package Chat.Rmi.Helpers;

public interface IFileWriter 
{
	public void CreateFileIfNotExist();
	public void WriteCredentials(String username,String password);
}
