package Chat.Rmi.Models;

public class UserNameOrPasswordInvalidException extends Exception
{
	public UserNameOrPasswordInvalidException()
	{
		super("The username or password is invalid!");
	}
}
