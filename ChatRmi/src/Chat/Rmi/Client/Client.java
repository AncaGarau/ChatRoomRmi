package Chat.Rmi.Client;

import Chat.Rmi.Models.User;

public class Client 
{
	private User user;
	
	public User GetUser() throws Exception
	{
		if(user == null)
			throw new Exception("This client does not have a registered user!");
		return user;
	}
	
	public void SetUser(User user)
	{
		this.user = user;
	}
}
