package Chat.Rmi.Models;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean isRegistered;

	public User(String username, String password, boolean isRegistered)
	{
		this.username = username;
		this.password = password;
		this.isRegistered = isRegistered;
	}

	public String getPassword() 
	{
		return password;
	}

	public String getUsername() 
	{
		return username;
	}
	
	public boolean isRegistered()
	{
		return isRegistered;
	}
	
	public void setToRegistered()
	{
		this.isRegistered = true;
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.getUsername().equals(((User)o).getUsername()) &&  
				this.getPassword().equals(((User)o).getPassword());
	}
}
