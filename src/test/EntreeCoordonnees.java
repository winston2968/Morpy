package test;

import java.util.Scanner;

public class EntreeCoordonnees {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
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
	}

}
