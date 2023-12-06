
package graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import commandline.MorpionCMD;
import generalClasses.Client;
import generalClasses.Server;

public class MorpionGraph extends MorpionCMD {
	private JFrame fenetre;
	private JLabel label1;
	private JLabel label2;

	private JPanel panel1;
	private JPanel panel2;
	private GridBagLayout range;
	private GridBagConstraints c;
	public String entree = "";

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;

	public MorpionGraph() {

		// Création du plateau de jeu et de la fenêtre d'affichage
		for (int i = 0; i < this.grille.length; i = i + 1) {
			for (int j = 0; j < this.grille[i].length; j = j + 1) {
				this.grille[i][j] = '.';
			}
		}
		// instanciation de la fenêtre d'affichage
		this.fenetre = new JFrame("Morpion");
		this.fenetre.setSize(400, 400);
		this.fenetre.setResizable(true);
		this.fenetre.setLayout(null);
		this.fenetre.setVisible(true);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // on paramètre la fermeture de
		// la fenêtre

		// création des espaces de texte
		this.label1 = new JLabel();
		this.label2 = new JLabel();

		// création du support du titre
		this.panel1 = new JPanel();
		this.panel1.setBackground(Color.lightGray);
		this.panel1.add(label1);

		// création du GridLayout manager (merci M. Garric)
		this.range = new GridBagLayout();

		// test
		this.c = new GridBagConstraints();
		this.c.insets = new Insets(1, 1, 1, 1);
		// colonne 0
		this.c.gridx = 0;
		// ligne 0
		this.c.gridy = 0;

		// création du support du plateau
		this.panel2 = new JPanel();
		// this.panel2.setSize(100, 50);
		this.panel2.setBackground(Color.lightGray);
		this.panel2.setLayout(this.range);

		this.fenetre.setLayout(this.range);
		this.fenetre.add(this.panel1, c);

		this.c.gridy = 1;
		// this.c.
		this.fenetre.add(this.panel2, c);

		this.btn1 = new JButton();
		this.btn1.setBackground(Color.WHITE);
		this.btn2 = new JButton();
		this.btn2.setBackground(Color.WHITE);
		this.btn3 = new JButton();
		this.btn3.setBackground(Color.WHITE);
		this.btn4 = new JButton();
		this.btn4.setBackground(Color.WHITE);
		this.btn5 = new JButton();
		this.btn5.setBackground(Color.WHITE);
		this.btn6 = new JButton();
		this.btn6.setBackground(Color.WHITE);
		this.btn7 = new JButton();
		this.btn7.setBackground(Color.WHITE);
		this.btn8 = new JButton();
		this.btn8.setBackground(Color.WHITE);
		this.btn9 = new JButton();
		this.btn9.setBackground(Color.WHITE);

	}

	public void startGame() { // Initialisation du jeu
		this.label1.setFont(new Font("Serif", Font.BOLD, 42));
		this.label1.setText("Bienvenue");
		this.label2.setText("Choisissez votre mode de connexion");

		// création des boutons de choix
		JButton btnC = new JButton(" Client  ");
		btnC.setBackground(Color.WHITE);

		JButton btnS = new JButton("Serveur");
		btnS.setBackground(Color.WHITE);

		// on ajoute les boutons de choix dans le panel2
		this.c.gridx = 0;
		this.c.gridy = 1;
		panel2.add(btnC, c);
		this.c.gridx = 2;
		panel2.add(btnS, c);
		this.c.gridx = 0;
		this.c.gridy = 0;
		this.c.gridwidth = 3;

		this.panel2.add(this.label2, c);

		// on décrit ce qui arrive quand on clique sur les boutons
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexionMode = false;
				symbol = 'O';

				// on enlève les boutons de choix et le texte
				btnS.setVisible(false);
				btnC.setVisible(false);
				label2.setVisible(false);

				chooseDone();

			}
		});
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexionMode = true;
				symbol = 'X';

				// on enlève les boutons de choix et le texte
				btnS.setVisible(false);
				btnC.setVisible(false);

				chooseDone();
			}
		});

	}

	public static void main(String[] args) {
		MorpionGraph test = new MorpionGraph();
		test.startGame();
	}

	public void chooseDone() {
		if (!this.connexionMode) { // dans le cas où c'est le client
			this.client = new Client();

			JTextArea text = new JTextArea("ici");
			JButton lui = new JButton("envoyer");
			this.label1.setFont(new Font("Serif", Font.BOLD, 12));

			this.label1.setText("Entrez l'IP de votre adversaire");
			this.panel2.add(text, c);
			this.panel2.add(lui);

			lui.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lui.setVisible(false);
					text.setVisible(false);

					entree = text.getText();
					client.serverAdress = entree;
					client.connectToServer();
					text.setVisible(false);
					lui.setVisible(false);
				}
			});

		} else { // si c'est le serveur
			this.server = new Server();
			this.label1.setFont(new Font("Serif", Font.BOLD, 11));

			this.label2.setText("Votre adresse IP est : " + this.server.ipAdress);
			this.label1.setText("Vous devez transmettre votre adresse IP à votre adversaire");

			// bouton pour lancer la connexion
			JButton btnOK = new JButton("C'est parti !");
			btnOK.setBackground(Color.WHITE);
			this.c.gridx = 0;
			this.c.gridy = 1;
			this.panel2.add(btnOK, c);

			btnOK.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					server.connectToClient();
					label2.setVisible(false);
					btnOK.setVisible(false);
				}

			});

		}
	}

	public void plateau() {

		// initialisation du plateau de jeu en fonction de la grille

		JButton[][] tableau = { { btn1, btn2, btn3 }, { btn4, btn5, btn6 }, { btn7, btn8, btn9 } };

		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille[i].length; j++) {

				switch (this.grille[i][j]) {

				case 'X':
					tableau[i][j].setEnabled(false);
					tableau[i][j].setText("X");
					break;

				case 'O':
					tableau[i][j].setEnabled(false);
					tableau[i][j].setText("O");
					break;

				case '.':
					tableau[i][j].setEnabled(true);
					tableau[i][j].setText("  ");
					break;
				}
			}
		}

	}

	public void tour() {

		// Le serveur joue le premier coup pour lancer la partie sans récupérer le
		// tableau
		if (this.premierCoup && this.connexionMode) {
			this.premierCoup = false;
			this.label1.setText("Le serveur commence à jouer");

		} else {
			// Réception du coup joué
			this.recevoir();

			// Vérification si le coup joué par l'adversaire n'est pas gagnant
			if (this.partieFinie()) {
				this.label1.setText("Vous avez perdu !");
				System.exit(0);
			}
		}

		// Séparation grahique de l'ancien coup et affichage de la grille

		this.label1.setText("À votre tour de jouer");
		this.c.gridx = 0;
		this.c.gridy = 1;
		this.panel2.add(this.btn1);
		this.c.gridx = 1;
		this.panel2.add(this.btn2);
		this.c.gridx = 2;
		this.panel2.add(this.btn3);
		this.c.gridx = 0;
		this.c.gridy = 2;
		this.panel2.add(this.btn4);
		this.c.gridx = 1;
		this.panel2.add(this.btn5);
		this.c.gridx = 2;
		this.panel2.add(this.btn6);
		this.c.gridx = 0;
		this.c.gridy = 3;
		this.panel2.add(this.btn7);
		this.c.gridx = 1;
		this.panel2.add(this.btn8);
		this.c.gridx = 2;
		this.panel2.add(this.btn9);

		JButton[][] tableau = { { this.btn1, this.btn2, this.btn3 }, { this.btn4, this.btn5, this.btn6 },
				{ this.btn7, this.btn8, this.btn9 } };

		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille[i].length; j++) {
				JButton btn = tableau[i][j];
				final int a = i;
				final int b = j;
				btn.addActionListener(new ActionListener() { // on ajoute un écouteur d'action
					public void actionPerformed(ActionEvent e) {
						grille[a][b] = symbol; // met a jour le tableau avec le nouveau coup joué

					}
				});

			}
		}

		// Vérification que le coup entré n'est pas gagnant
		if (this.partieFinie()) {
			this.label2.setText("Vous avez gagné !");
			this.label2.setVisible(true);
			this.envoyer();
			System.exit(0);
		}

		// Envoi du coup joué
		this.envoyer();
	}
	
	public boolean partieFinie() { // Test si une partie est finie
		if (this.tableau_plein() && !this.gagne()) {
			this.label1.setText("La partie est finie !");
			this.label2.setText("Aucun des deux joueurs n'a gagné !");
			this.label2.setVisible(true);
			System.exit(0);
		} else if (this.gagne()) {
			this.label1.setText("La partie est finie !");
			return true ;
		} 
		return false ;
	}

}
