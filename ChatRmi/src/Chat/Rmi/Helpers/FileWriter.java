package Chat.Rmi.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileWriter implements IFileWriter{
	
	File file = new File("Credentials.txt");
	@Override
	public void createFileIfNotExist() {
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void writeCredentials(String username,String password) {
		String credentials=username+","+password;
		
		try {
			java.io.FileWriter fw=new java.io.FileWriter(file,true);
			fw.append(credentials);
			fw.append(System.getProperty("line.separator"));
			fw.close();
			
			System.out.println("Username saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
