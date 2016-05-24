package Chat.Rmi.Models;

public class ServerAddressFileException extends Exception
{
	public ServerAddressFileException()
	{
		super(LocalizedStrings.AddressFileError);
	}

}
