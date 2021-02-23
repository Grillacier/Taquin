package game_test;

import java.util.ArrayList;

public class Cellule {
	private Cellule parent;
	private int x,y;
	private int g,h,f ;
	public int [][]tab;
	private int valGlobalX, valGlobalY;
	private int valGlobalG = 0;
	private ArrayList<Cellule> entree = new ArrayList<Cellule>();
	private ArrayList<Cellule> sortie = new ArrayList<Cellule>();
	
	public Cellule (int [][]tab) {
		initialisationValXY (tab);
		this.tab = tab;
		this.g = 0;
		this.h = distanceHammingHeuristique(this);
		this.f = this.g+this.h;
		
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}
	
	public int getG () {
		return this.g;
	}
	
	public int getH () {
		return this.h;
	}
	
	public int getF() {
		return this.f;
	}
	// les méthodes Mouvements vérifie si un mouvement spécifique est possible ou non
	public boolean MouvementBas (Cellule c) {
				if (c.x==c.tab.length-1 && c.tab[c.x][c.y]==0) {
					return false;
				}
				
				return true;
		}
	
	public boolean MouvementHaut(Cellule c) {
				if (c.x==0 && c.tab[c.x][c.y]==0) {
					return false;
				}
				
				return true;
		}
	
	public boolean MouvementGauche(Cellule c) {
				if (c.y==0 && c.tab[c.x][c.y]==0) {
					return false;
				}
		
				return true;
	}
	
	public boolean MouvementDroit(Cellule c) {
				if (c.y==this.tab.length-1 && c.tab[c.x][c.y]==0) {
					return false;
				}
		
				return true;
		}
	//construit un tableau avec les coordonnées x et y déplacée en bas
	public int [][] constrTabBas(int[][]tab){
		int [][]tabBas = new int [tab.length][tab[0].length];
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (i != tab.length-1 && tab[i][j]==0) {
					int tmp = tab[i][j];
					tab[i][j] = tab[i+1][j];
					tab[i+1][j] = tmp;
				}
			}
		}
		tabBas = tab;
		//this.tab = tabBas;
		return tabBas;
	}
	//construit un tableau avec les coordonnées x et y déplacée en haut
	public int [][] constrTabHaut(int[][]tab){
		int [][]tabHaut = new int [tab.length][tab[0].length];
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (i != 0 && tab[i][j]==0) {
					int tmp = tab[i][j];
					tab[i][j] = tab[i-1][j];
					tab[i-1][j] = tmp;
				}
			}
		}
		tabHaut = tab;
		//this.tab = tabHaut;
		return tabHaut;
	}
	//construit un tableau avec les coordonnées x et y déplacée à gauche
	public int [][] constrTabGauche(int[][]tab){
		int [][]tabGauche = new int [tab.length][tab[0].length];
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (j != 0 && tab[i][j]==0) {
					int tmp = tab[i][j];
					tab[i][j] = tab[i][j-1];
					tab[i][j-1] = tmp;
				}
			}
		}
		tabGauche = tab;
		//this.tab = tabGauche;
		return tabGauche;
	}
	// construit un tableau avec les coordonnées x et y déplacée à droite
	public int [][] constrTabDroit(int[][]tab){
		int [][]tabDroit = new int [tab.length][tab[0].length];
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (j != tab.length-1 && tab[i][j]==0) {
					int tmp = tab[i][j];
					tab[i][j] = tab[i][j+1];
					tab[i][j+1] = tmp;
				}
			}
		}
		tabDroit = tab;
		//this.tab = tabDroit;
		return tabDroit;
	}
	// méthode qui permet d'obtenir la configuration finale du taquin
	public int[][] tabFinal(int [][]c) {
		int [][]tabFinal = new int [c.length][c[0].length];
		int k = 1;
		for (int i=0; i<tabFinal.length; i++) {
			for (int j=0; j<tabFinal[i].length; j++) {
				tabFinal[i][j] = k;
				k++;
			}
		}
		for (int i=0; i<tabFinal.length; i++) {
			for (int j=0; j<tabFinal[i].length; j++) {
				tabFinal[tabFinal.length-1][tabFinal[0].length-1] = 0;
						
				}
			}
		
		return tabFinal;
	}
	//permet d'initialiser les valeurs x et y d'une cellule par rapport à un tableau donné	
	public void initialisationValXY (int [][]tab) {
		
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				if (tab[i][j]==0) {
					this.x = i;
					this.y = j;
				}
			}
		}
		
	}
	

	//permet de calculler le nombre d'éléments mal placé dans le tableau d'une cellule
	public int distanceHammingHeuristique(Cellule c) {
		int [][] finale = tabFinal(c.tab);
		int k = 0;
		for (int i=0; i<c.tab.length; i++) {
			for (int j=0; j<c.tab[i].length; j++) {
				if (tab[i][j]!=finale[i][j] && tab[i][j]!=0) {
					k++;
				}
			}
		}
		
		return k; 
		
	}
	//méthode qui permet de trouver la configuration la moins couteuse parmi les mouvements possibles de la cellule
	public Cellule fminValCellule (Cellule c) {
		ArrayList<Cellule> fval = new ArrayList<Cellule>();
		Cellule cellFmin = null; //initialisation
		//Cellule cellB = new Cellule (new int [c.tab.length][c.tab.length]);
		if (MouvementBas(c)) {
			int [][]tabB = constrTabBas(c.tab) ;
			Cellule cellB = new Cellule (tabB);
			cellB.x = c.x+1 ;
			cellB.g++;
			cellB.h = distanceHammingHeuristique(cellB);
			cellB.f = cellB.g + cellB.h;
			fval.add(cellB);
		}
		else if (MouvementHaut(c)) {
			int [][]tabH = constrTabHaut(c.tab);
			Cellule cellH = new Cellule (c.tab);
			cellH.x = c.x-1;
			cellH.g++;
			cellH.h = distanceHammingHeuristique(cellH);
			cellH.f = cellH.g + cellH.h;
			fval.add(cellH);
		}
		else if (MouvementGauche(c)) {
			int [][]tabG = constrTabGauche(c.tab) ;
			Cellule cellG = new Cellule (tabG);
			cellG.y = c.y-1;
			cellG.g++;
			cellG.h = distanceHammingHeuristique(cellG);
			cellG.f = cellG.g + cellG.h;
			fval.add(cellG);
		}
		else if (MouvementDroit(c)) {
			int [][]tabD = constrTabDroit(c.tab) ;
			Cellule cellD = new Cellule (tabD);
			cellD.y = c.y+1;
			cellD.g++;
			cellD.h = distanceHammingHeuristique(cellD);
			cellD.f = cellD.g + cellD.h;
			fval.add(cellD);
		}
		int fminVal = fval.get(0).f; //initialisation
		for (int i=1; i<fval.size(); i++) {
			if (fval.get(i).f<fminVal) {
				fminVal = fval.get(i).f;
			}
		}
		
		for (int i=0; i<fval.size(); i++) {
			if (fminVal == fval.get(i).f) {
				cellFmin = fval.get(i);
			}
		}
		
		return cellFmin;
	}
	//vérifie si la configuration du tableau d'une cellule est la même que la configuration finale
	public boolean estAtteint(Cellule c) { 
		for (int i=0; i<c.tab.length; i++) {
			for (int j=0; j<c.tab[i].length; j++) {
				if (c.tab[i][j]==tabFinal(c.tab)[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void algorithme () { //tu peux changer le type de retour stv !!
	
		// À COMPLETER!
	}
}

	
	

