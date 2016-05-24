package Chat.Rmi.Models;

public class LocalizedStrings 
{
	public static String WelcomeToChat = "\n\nWelcome to the chat!\n\n";
	public static String ProblemCommunicating = "\nThere was a problem communicating with the server! Please try to log in again later!\n";
	public static String ErrorTryingToConnect = "\nThere was an error trying to connect to the server. Please try again later.\n";
	public static String ServerAddressFile = "C:\\Chat\\ServerAddress.config";
	public static String Menu = "\n1.Log in\n2.Register\nEnter your option...\n";
	public static String InvalidOption = "Invalid option! Please try again: ";
	public static String EnterUser = "Enter username:";
	public static String EnterPassword = "Enter password:";
	public static String EnterNewUser = "Enter new user name:";
	public static String EnterNewPassword = "Enter new password:";
	public static String UsersFile = "C:\\Chat\\Users.txt";
	public static String UserSaved = "\nUsername saved.\n";
	public static String CredentialVaildationRegex = "^[a-zA-Z0-9]+$";
	public static String InvalidClientOption = "Invalid client option! Please do not call this method with any other option except 1 or 2!";
	public static String AddressFileError = "There was an error while reading the server address!";
	public static String UsersFileError = "There was an error while trying to write to the users file! The user was not saved!";
	public static String HasSignedIn = " has signed in!";
	public static String InvalidCredentials = "Invalid credentials!\n";
	public static String InvalidUserName = "Invalid user name!\n";
	public static String InvalidPassword = "Invalid password!\n";
	public static String UserAlreadyExists = "User name already exists!\n";
	public static String DanceShortcut = "-dance";
	public static String DanceEmoticon = "\n\\('o')\n  ) )Z\n  /\\";
	public static String CatShortcut = "-cat";
	public static String CatEmoticon = "\n___,,,^._.^,,,___";
	public static String RockShortcut = "-rock";
	public static String RockEmoticon = "\n\\m/(`o`)\\m/";
}
