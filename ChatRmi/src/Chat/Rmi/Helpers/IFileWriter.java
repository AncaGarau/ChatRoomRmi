package Chat.Rmi.Helpers;

public interface IFileWriter {
	public void createFileIfNotExist();
	public void writeCredentials(String username,String password);
}
