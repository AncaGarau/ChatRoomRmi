package Chat.Rmi.Models;

public class InvalidUserNameException extends Exception
{
	public InvalidUserNameException()
	{
		super("The username is invalid!");
	}

}
