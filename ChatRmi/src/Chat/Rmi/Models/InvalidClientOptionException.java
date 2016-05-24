package Chat.Rmi.Models;

public class InvalidClientOptionException extends Exception
{
	public InvalidClientOptionException()
	{
		super(LocalizedStrings.InvalidClientOption);
	}
}
