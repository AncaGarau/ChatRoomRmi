package Chat.Rmi.Models;

public class DuplicateUserNameException extends Exception
{
	public DuplicateUserNameException()
	{
		super("This username already exists!");
	}
}
