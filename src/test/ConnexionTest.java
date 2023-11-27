package test;

import generalClasses.Client;
import generalClasses.Server;

public class ConnexionTest {
	
	public static void main(String[] args) {
		Client c1 = new Client();
		Server s1 = new Server();
		
		c1.serverAdress = "192.168.195.190";
		c1.connectToServer();
		c1.sendToServer("salut du client");
		System.out.println(c1.readFromServer());
		
	}

}
