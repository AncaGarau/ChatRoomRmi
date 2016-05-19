package Chat.Rmi.Server;


import Chat.Rmi.Helpers.FileReader;
import Chat.Rmi.Helpers.FileWriter;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileWriter fw=new FileWriter();
		fw.createFileIfNotExist();
		//fw.writeCredentials("andreea", "123dsd12");
		//fw.writeCredentials("alex", "parola");
		//fw.writeCredentials("ana", "paroladdd");
		FileReader fr=new FileReader();
		fr.ReadCredentials("Credentials.txt");
	
	}

}
