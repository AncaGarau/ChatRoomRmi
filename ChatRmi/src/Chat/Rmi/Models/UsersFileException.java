package Chat.Rmi.Models;

public class UsersFileException extends Exception
{
	public UsersFileException()
	{
		super(LocalizedStrings.UsersFileError);
	}

}
