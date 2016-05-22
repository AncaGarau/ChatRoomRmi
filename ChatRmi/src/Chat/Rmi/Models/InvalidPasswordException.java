package Chat.Rmi.Models;

public class InvalidPasswordException extends Exception
{
	public InvalidPasswordException()
	{
		super("Password is invalid!");
	}

}
