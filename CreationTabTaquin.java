package game_test;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class CreationTabTaquin {

	public static void melangerTab(int[][] tab) { //permet de mélanger un tableau donner en argument changeant aléatoirement les éléments du tableau
		// Cette foncion permet également d'éviter la répétition de nombre dans le tableau
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				int i1 = (int)(Math.random()*tab.length-1); // choisi un indice i entre 0 et la taille du tableau -1
				int j1 = (int)(Math.random()*tab[i].length-1);
				int temp = tab[i][j];
				tab[i][j] = tab[i1][j1];
				tab[i1][j1] = temp;
				// échange simplement de manière aléatoire les éléments des indices
			}
		}
	}
	 

	  
	
	
	public  int[][] tabNombreAleatoire (int ligne, int colonne){ //Créée un tableau avec des nombre aléatoire compris de 0 à (ligne*colonne)-1
		int [][]tabTaquin = new int [ligne][colonne];
		int x = 0;
		// j'ajoute dans un premier temps dans le tableau tabTaquin des chiffres compris entre 0 et (ligne * colonne)-1
		for(int i=0; i < ligne; i++) {
			for (int j=0; j<colonne; j++) {
				tabTaquin[i][j] = x;
				x++;
					
				}
			}

		melangerTab(tabTaquin); // Je mélange le tableau aléatoirement
		
		return tabTaquin; // Je retourne le tableau
	}
}
	


