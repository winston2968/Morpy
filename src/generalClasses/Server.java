package generalClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	
	public String ipAdress ;
	public ServerSocket server ;
	public Socket client ;
	public BufferedReader in ;
	public PrintWriter out ;
	
	
	public Server() {
		try {
			this.ipAdress = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("Impossible de récupérer l'adresse ip de la machine !");
			System.exit(0);
		}
		
	}
	
	
	public void connectToClient() {
		System.out.println("Morpy/:$ Connexion au client...");
		try {
			this.server = new ServerSocket(4444);
			this.client = this.server.accept();
			this.in = new BufferedReader(new InputStreamReader (client.getInputStream()));
			this.out = new PrintWriter(client.getOutputStream(), true);
			System.out.println("Morpy/:$ Connexion réussie !");
		} catch (Exception e) {
			System.out.println("Morpy/:$ Erreur : connexion échouée !");
			System.exit(0);
		}
		
	}
	
	public void sendToClient(String text) {
		this.out.println(text);
	}
	
	public String readFromClient() {
		try {
			return this.in.readLine().toString();
		} catch (IOException e) {
			return "";
		}
	}
	

}
