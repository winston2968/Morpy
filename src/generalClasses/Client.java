package generalClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public String serverAdress ;
	public Socket client ;
	public BufferedReader in ;
	public PrintWriter out ;
	
	public Client() {
		
	}
	
	public void connectToServer() {
		System.out.println("Morpy/:$ Connexion au serveur...");
		try {
			this.client = new Socket(this.serverAdress, 4444);
			this.in = new BufferedReader(new InputStreamReader (client.getInputStream()));
			this.out = new PrintWriter(client.getOutputStream(), true);
			System.out.println("Morpy/:$ Connexion réussie !");
		} catch (Exception e) {
			System.out.println("Morpy/:$ Erreur : Echec de la connexion !!!");
			System.exit(0);
		}
		
		
	}
	
	public void sendToServer(String text) {
		this.out.println(text);
	}
	
	public String readFromServer() {
		try {
			return this.in.readLine().toString();
		} catch (IOException e) {
			return "";
		}
	}
	

}
