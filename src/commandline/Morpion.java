package commandline;

import java.util.Arrays;
import java.util.Scanner;
import generalClasses.Client;
import generalClasses.Server;

public class Morpion {
	
	public char[][] grille = new char[3][3]; 				// plateau de jeu
	public boolean connexionMode ; 							// true si serveur ou false si client
	public char symbol ; 									// Symbole par lequel le joueur remplit le tableau
	public Boolean premierCoup = true ;
	Client client ;
	Server server ;

	
	public Morpion() {
		
		// Création du plateau de jeu
		for (int i = 0; i < this.grille.length; i = i + 1) {
			for (int j = 0; j < this.grille[i].length; j = j + 1) {
				this.grille[i][j] = '.';
			}
		}
	}
	
	/*
	 * Problème split[2] est nul au second coup. Peut être la récupération du tableau. Mauvais formatage
	 */
	

	
	/*
	 * ================================================================================================================================================================
	 * 															Méthodes pour la gestion du jeu
	 * ================================================================================================================================================================
	 */
	
		
	public void startGame() { // Initialisation du jeu
		System.out.println("Bienvenue \n");
		
		// Choix du mode de connexion pour chaque joueur
		Scanner scan = new Scanner(System.in);
		System.out.print("""
				Morpy/:$ --- Choisissez votre mode de connexion (un serveur et un client par partie):
								1 -> Client
								2 -> Serveur
				Morpy/:$ """);
		int nombre = scan.nextInt();
		
		// Attribution des modes de connexion et des symboles 
		if (nombre == 1) {
			this.connexionMode = false ;
			this.symbol = 'O';
		} else {
			this.connexionMode = true ;
			this.symbol = 'X';
		}
		
		// Connexion en fonction du type de chaque joueur
		if (this.connexionMode == true) {
			this.server = new Server();
			System.out.println("""
					Morpy/:$ --- | Vous avez choisit une connexion serveur :
						     | Communiquez l'adresse ci-dessous à votre partenaire pour qu'il se connecte
						     | Adresse : """ + this.server.ipAdress);
			this.server.connectToClient();
			
		} else {
			this.client = new Client() ;
			scan.nextLine(); // Pour récupérer le "\n" restant de l'entrée int précédente
			System.out.print("""
					Morpy/:$ --- | Vous avez choisit une connexion client.
					         | Entrez l'adresse comuniquée par votre adversaire ---
					Morpy/:$ """);
			String entree = scan.nextLine();
			System.out.println("Entrée : " + entree);
			this.client.serverAdress = entree ;
			this.client.connectToServer();
		}
		
	}
	
	
	public void envoyer() { // Envoi du tableau à l'autre joueur
		if (this.connexionMode) {
			// Cas du serveur
			this.server.sendToClient(Arrays.asList(this.grille).toString());
		} else {
			// Cas du client
			this.client.sendToServer(Arrays.asList(this.grille).toString());
		}
	}
	
	
	public void recevoir() {
		String[] split1 ;
		if (this.connexionMode) {
			// Cas du serveur
			split1 = this.server.readFromClient().split("\n");			
		} else {
			split1 = this.client.readFromServer().split("\n");
		}
		System.out.println("Message reçu : " + Arrays.asList(split1).toString());
		// Formatage du retour pour avoir un tableau de caractères
		String[][] split2 = new String[3][];
		for (int i = 0; i < split1.length ;i++) {
			split2[i] = split1[i].split(" ");
		}
		char[][] retour = new char[3][3];
		for (int i = 0 ; i < split2.length ; i++) {
			for (int j = 0 ; j < split2[i].length ; j++) {
				retour[i][j] = split2[i][j].charAt(0);
			}
		}
		// Mise à jour du plateau
		this.grille = retour ;
		
	}
	
	
	/*
	 * ================================================================================================================================================================
	 * 															Gestion d'un tour
	 * ================================================================================================================================================================
	 */
	
	public void tour() {
		
		// Le serveur joue le premier coup pour lancer la partie sans récupérer le tableau
		if (!this.premierCoup || !this.connexionMode) {
			this.premierCoup = false ;
			
			// Réception du coup joué
			this.recevoir();
			
			// Vérification si le coup joué par l'adversaire n'est pas gagnant
			if (this.partieFinie()) {
				System.out.println("Morpy/:$ --- Votre adversaire a gagné !");
			}
		}		
		
		// Séparation grahique de l'ancien coup
		System.out.println("""
				=========================================================================
				Morpy/:$ --- A votre tour de jouer !
				""");
		System.out.println("\n" + this.affiche() + "\n");
		
		// Récupération des coordonnées
		int[] coord = this.getCoordonnees();

		while (!this.case_vide(coord[0] - 1, coord[1] - 1)) {
			System.out.println("Morpy/:$ --- /!\\ La case saisie n'est pas libre ! /!\\");
			coord = this.getCoordonnees();
		}
		
		// Entrée de la nouvelle valeur dans le tableau
		this.grille[coord[0] - 1][coord[1] - 1] = this.symbol ;
		
		// Vérification que le coup entré n'est pas gagnant
		if (this.partieFinie()) {
			System.out.println("Morpy/:$ --- Vous avez gagné !");
			System.exit(0);
		} 
		
		// Envoi du coup joué
		this.envoyer();
	}
	
	
	/*
	 * ================================================================================================================================================================
	 * 															Méthodes pour la gestion du plateau
	 * ================================================================================================================================================================
	 */
	
	
	
	public String affiche() { // Renvoie le plateau
		String retour = "" ;
		for (int i = 0; i < this.grille.length; i = i + 1) {
			for (int j = 0; j < this.grille[i].length; j = j + 1) {
				retour += this.grille[i][j] + " ";
			}
			retour += "\n";
		}
		return retour ;
	}
	
	public char[][] getPlateau() {
		return this.getPlateau();
	}
	
	
	public int[] getCoordonnees() { // Demande à l'utilisateur de saisir le coup à jouer 
		Scanner scan = new Scanner(System.in);
		// scan.next();
		int[] retour = new int[2] ;
		retour[0] = -1 ;
		retour[1] = -1 ;
		while ((retour[0] >= 1 && retour[0] <= 3) == false || (retour[1] >= 1 && retour[1] <= 3) == false) {
			System.out.println("Morpy/:$ --- Entrez une ligne :");
			System.out.print("Morpy/:$ ");
			retour[0] = scan.nextInt();
			System.out.println("Morpy/:$ --- Entrez une colonne :");
			System.out.print("Morpy/:$ ");
			retour[1] = scan.nextInt();	
		}
		return retour ;
	}


	public boolean case_vide(int i, int j) { // Vérifie si une case du plateau est vide 
		if (this.grille[i][j]=='.' ) {
			return true;
		}
		return false;
	}
	
	public boolean ligne_vide(int i) { // Vérifie si une ligne est vide (utile plus tard)
		if (this.case_vide(i, 0) && this.case_vide(i, 1) && this.case_vide(i, 2)) {
			return true;
		}
		return false;
	}
	
	public boolean partieFinie() { // Test si une partie est finie
		if (this.tableau_plein() && !this.gagne()) {
			System.out.println("Morpy/:$ --- PARTIE FINIE --- ");
			System.out.println("Morpy/:$ --- Aucun des deux joueurs n'a gagné !");
			System.exit(0);
		} else if (this.gagne()) {
			System.out.println("Morpy/:$ --- Partie Finie --- ");
			return true ;
		} 
		return false ;
	}
	
	public boolean tableau_plein() { // Test si le plateau est plein
		if (!this.ligne_vide(0) && !this.ligne_vide(1) && !this.ligne_vide(2)) {
			return true;
		}
		return false;
	}
	
	public boolean gagne() { // Test si une partie est gagnée (sous cas d'une partie finie)
		for (int i = 0; i < this.grille.length; i = i + 1) {
			if ((this.grille[i][0] == this.grille[i][1] && this.grille[i][1] == this.grille[i][2])
					&& this.grille[i][0] != '.') {
				return true;
			} else if ((this.grille[0][i] == this.grille[1][i] && this.grille[1][i] == this.grille[2][i])
					&& this.grille[0][i] != '.') {
				return true;
			} else if ((this.grille[0][0] == this.grille[1][1] && this.grille[1][1] == this.grille[2][2])
					&& this.grille[0][0] != '.') {
				return true;
			}
		}
		return false;
	}

}
