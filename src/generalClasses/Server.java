package generalClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Server {
	
	public String ipAdress ;
	public ServerSocket server ;
	public Socket client ;
	public BufferedReader in ;
	public PrintWriter out ;
	
	
	public Server() {
		
		try {
			// Récupération de l'adresse ip de la machine serveur
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();	
			while(e.hasMoreElements()) {
				// Récupère une énumération de toutes les interfaces réseau
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    Enumeration<InetAddress> ee = n.getInetAddresses();
			    while (ee.hasMoreElements()) {
			    	// Récupère l'adresse ip de chaque interface
			        InetAddress i = (InetAddress) ee.nextElement();
			        String current = i.getHostAddress();
			        // Choisit celle que l'on veut
			        String[] decoupage = current.split("\\.");
			        if (decoupage.length == 4 && current.equals("127.0.0.1") == false) {
			        	this.ipAdress = current ;	        	
			        }
			        
			    }
			}
		} catch (Exception e) {
			System.out.println("Morpy/:$ --- /!\\ Impossible de récupérer l'adresse ip de la machine /!\\");
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
