package Chat.Rmi.Client;

import java.util.Scanner;

import Chat.Rmi.Server.Server;

public class Launcher {

	public static void main(String[] args)
	{
		IClient client;

		String welcomMessage="1.Register User\n2.Login user\n3.Exit chat\nEnter your option...\n";
		
		System.out.println(welcomMessage);
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();
		
		while(option<1 && option>3)
		{
			System.out.println("Wrong option try again...");
			System.out.println(welcomMessage);
			option = scanner.nextInt();
		}
		
		if(option>=1 && option<=2)
		{		
			String username="",
				   password="";
			
			switch(option)
			{
				case 1: System.out.println("Enter new user name");
					username = scanner.nextLine();
					System.out.println("Eneter new password");
					password = scanner.nextLine();
					//client = new Client(new User(newUsername, newPassword), new Server());
				case 2: System.out.println("Enter login user");
					username = scanner.nextLine();
					System.out.println("Enter login password");
					password = scanner.nextLine();
					//client = new Client(new User(username, password), new Server()); 
			}
		}
	}

}
