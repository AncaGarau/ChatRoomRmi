package Chat.Rmi.Models;

public class ServerAddress 
{
	private String ip;
	private int port;
	private String name;
	
	public ServerAddress(String ip, int port, String name)
	{
		this.ip = ip;
		this.port = port;
		this.name = name;
	}
	
	public String GetIp()
	{
		return this.ip;
	}
	
	public int GetPort()
	{
		return this.port;
	}
	
	public String GetName()
	{
		return this.name;
	}

}
