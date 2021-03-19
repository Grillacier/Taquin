
public class Plateau {
    public  Case[][] plateau; // double tableau
    public int hauteur, largeur; // taille du tableau
    private int x , y; // coordonnées de la case vide
    private final int compteur;
    private final Plateau precedent;

    public Plateau(int h, int l){
        this.hauteur = h;
        this.largeur = l;
        this.plateau = tabNombreAleatoire(h, l);
        initialisationValXY ();
        this.compteur = 0;
        this.precedent = null;
    }
    public Plateau(int c,Plateau p){
        this.hauteur = p.hauteur;
        this.largeur = p.largeur;
        this.compteur = c;
        this.precedent =p;
        for(int i = 0;i<this.hauteur;i++){
          for(int j = 0;i<this.largeur;j++){
            this.plateau[i][j]= p.plateau[i][j];
          }
        }

    }
    public Case[][]getPlateau () {
    	return this.plateau;
    }

    public Plateau getPrecedent(){
      return this.precedent;
    }

    public int getCompteur(){
      return this.compteur;
    }
    
  //copie le tableau initiale
    public Plateau copiePlateau() {
    	Plateau copie = new Plateau(this.hauteur,this.largeur);
    	for (int i=0; i<copie.plateau.length; i++) {
  			for (int j=0; j<copie.plateau[i].length; j++) {
  				copie.plateau[i][j] = this.plateau[i][j];
  			}
    	}
    	return copie;
    }
    
    //permet d'initialiser les valeurs x et y de la case vide du plateau
  	public void initialisationValXY () {
  		
  		for (int i=0; i<this.plateau.length; i++) {
  			for (int j=0; j<this.plateau[i].length; j++) {
  				if (this.plateau[i][j].getNumero()==0) {
  					this.x = i;
  					this.y = j;
  				}
  			}
  		}
  	}
  	
    public static void melangerTab(Case[][] tab) { //permet de mélanger un tableau donner en argument changeant aléatoirement les éléments du tableau
		// Cette foncion permet également d'éviter la répétition de nombre dans le tableau
		for (int i=0; i<tab.length; i++) {
			for (int j=0; j<tab[i].length; j++) {
				int i1 = (int)(Math.random()*tab.length-1); // choisi un indice i entre 0 et la taille du tableau -1
				int j1 = (int)(Math.random()*tab[i].length-1);
				Case temp = tab[i][j];
				tab[i][j] = tab[i1][j1];
				tab[i1][j1] = temp;
				// échange simplement de manière aléatoire les éléments des indices
			}
		}
	}

    public  Case[][] tabNombreAleatoire (int ligne, int colonne){ //Créée un tableau avec des nombre aléatoire compris de 0 à (ligne*colonne)-1
		Case [][]tabTaquin = new Case [ligne][colonne];
		int x = 0;
		boolean val = false;
		// ajoute dans un premier temps dans le tableau tabTaquin des chiffres compris entre 0 et (ligne * colonne)-1
		for(int i=0; i < ligne; i++) {
			for (int j=0; j<colonne; j++) {
				tabTaquin[i][j] = new Case(x);
				x++;
					
				}
			}
	
		melangerTab(tabTaquin); //mélange le tableau aléatoirement
		while (!estSoluble(tabTaquin)) {
    		melangerTab(tabTaquin);
    		
    	} 	
			//afficher();//melangerTab(tabTaquin);
			//System.out.println(estSoluble(this.plateau));
			
		return tabTaquin;
	}
    
    public boolean MouvementBas () {
			if (this.x==this.hauteur-1) {
				return false;
			}
			
			return true;
	}

public boolean MouvementHaut() {
			if (this.x==0) {
				return false;
			}
			
			return true;
	}

public boolean MouvementGauche() {
			if (this.y==0 ) {
				return false;
			}
	
			return true;
}

public boolean MouvementDroit() {
			if (this.y==this.largeur-1) {
				return false;
			}
	
			return true;
	}

//construit un tableau avec les coordonnées x et y déplacée en bas
	public Plateau constrTabBas(){
		Plateau tabBas = copiePlateau();
		for (int i=0; i<tabBas.hauteur; i++) {
			for (int j=0; j<tabBas.plateau[i].length; j++) {
				if (i != tabBas.plateau.length-1 && tabBas.plateau[i][j].getNumero()==0) {
					Case tmp = tabBas.plateau[i][j];
					tabBas.plateau[i][j] = tabBas.plateau[i+1][j];
					tabBas.plateau[i+1][j] = tmp;
					return tabBas;
				}
			}
		}
	
		return null;
	}
	//construit un tableau avec les coordonnées x et y déplacée en haut
	public  Plateau constrTabHaut(){
		Plateau tabHaut = copiePlateau();
		for (int i=0; i<tabHaut.hauteur; i++) {
			for (int j=0; j<tabHaut.plateau[i].length; j++) {
				if (i != 0 && tabHaut.plateau[i][j].getNumero()==0) {
					Case tmp = tabHaut.plateau[i][j];
					tabHaut.plateau[i][j] = tabHaut.plateau[i-1][j];
					tabHaut.plateau[i-1][j] = tmp;
					return tabHaut;
				}
			}
		}
		
		return null;
		}
		
	//construit un tableau avec les coordonnées x et y déplacée à gauche
	public Plateau constrTabGauche(){
		Plateau tabGauche = copiePlateau();
		for (int i=0; i<tabGauche.hauteur; i++) {
			for (int j=0; j<tabGauche.plateau[i].length; j++) {
				if (j != 0 && tabGauche.plateau[i][j].getNumero()==0) {
					Case tmp = tabGauche.plateau[i][j];
					tabGauche.plateau[i][j] = tabGauche.plateau[i][j-1];
					tabGauche.plateau[i][j-1] = tmp;
					return tabGauche;
				}
			}
		}
		
		return null;
	}
	// construit un tableau avec les coordonnées x et y déplacée à droite
	public Plateau constrTabDroit(){
		Plateau tabDroit = copiePlateau();
		for (int i=0; i<tabDroit.hauteur; i++) {
			for (int j=0; j<tabDroit.plateau[i].length; j++) {
				if (j != tabDroit.plateau[i].length-1 && tabDroit.plateau[i][j].getNumero()==0) {
					Case tmp = tabDroit.plateau[i][j];
					tabDroit.plateau[i][j] = tabDroit.plateau[i][j+1];
					tabDroit.plateau[i][j+1] = tmp;
					return tabDroit;
				}
			}
		}
		
		return null;
		
	}
    
    public void afficher(){
        char c = 'A';
        int size;
        System.out.print("   "); // espaces de la premiere ligne
        for(int i=0; i<this.largeur; i++) // affiche la premiere ligne
            System.out.print(i+1 + "  ");
        System.out.println();

        for(int i=0; i<this.hauteur; i++){
            System.out.print(c + "  "); // affiche la premiere colonne
            for(int j=0; j<this.largeur; j++){
                System.out.print(this.plateau[i][j].getNumero() + " "); // affiche le num
                size = String.valueOf(this.plateau[i][j].getNumero()).length();
                if(size == 1){ // si la taille=1, ajouter un espace
                    System.out.print(" ");
                }
            }
            System.out.println();
            c++;
        }
    }


    /**
     * V&eacute;rifie si le plateau est en position gagnante
     * @param tab un double tableau de Cases
     * @return true si tab est en position gagnante, false sinon
     */
    public boolean jeuGagne(){ //fonction qui évalue si le jeu est terminé (gagné) ou pas


        int k=1;
        for(int i=0; i<this.plateau.length; i++){
            for(int j=0; j<this.plateau[i].length; j++){
              if(i == (this.hauteur-1) && j == (this.largeur-1) && this.plateau[i][j].getNumero() == 0){
                  return true;
              }

                if(this.plateau[i][j].getNumero()!=k){
                    return false;
                  }
                k++;
            }
        }
        return true;
    }


    /** Permet de deplacer la Case cible si le deplacement est valide
     *
     *@param cibleh Coordonnée y (hauteur)
     *@param ciblel Coordonnée x (largeur)
     */

    public void mouvement(String type){
      int x = -1;
      int y = -1;
      for(int i = 0;i<this.hauteur;i++){
        for(int j = 0;j<this.largeur;j++){
            if(this.plateau[i][j].getNumero() == 0){
               x = j;
               y = i;

            }
        }
      }

        if(type.equals("haut") && y>0){
          Case tmp = plateau[y][x];
          plateau[y][x] = plateau[y-1][x];
          plateau[y-1][x] =tmp;
          return;
        }
        if(type.equals("bas") && y<this.hauteur-1){
          Case tmp = plateau[y][x];
          plateau[y][x] = plateau[y+1][x];
          plateau[y+1][x] =tmp;
          return;
        }
        if(type.equals("droite") && x<this.largeur-1){
          Case tmp = plateau[y][x];
          plateau[y][x] = plateau[y][x+1];
          plateau[y][x+1] =tmp;
          return;
        }
        if(type.equals("gauche") && x>0){
          Case tmp = plateau[y][x];
          plateau[y][x] = plateau[y][x-1];
          plateau[y][x-1] =tmp;
          return;
        }


        }
      //  System.out.println("Impossible de deplacer la case de cette facon:  "+type);

    /**
     * Convertit un double tableau de Cases en un tableau d'entiers
     * @return un tableau d'entiers contenant les num&eacute;ros des Cases sans en changer l'ordre
     */
    public int[] conversion(Case[][]tab) {
        int[] tab2 = new int[tab.length*tab[0].length];
        int k = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab2[k] = tab[i][j].getNumero();
                k++;
            }
        }
        return tab2;
    }


    /**
     * Tri un tableau d'entiers dans l'ordre croissant avec le 0 a la fin
     * @param p Tableau tableau de tableaux de Cases dont on va trier les cases
     * @return le nombre d'&eacute;changes et de d&eacute;placements du 0
     */
    private int[] tri(Case[][] p) {
        int[] tab = conversion(p); // plateau convertit en tableau d'entiers
        int[] res = new int[2];
        int nbEchanges = 0; // nombre de deplacements total des Cases
        int parite = 0; // nombre de deplacements du 0
        int tmp = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 1; j < tab.length-i; j++) {
                if (tab[j-1] == 0) {
                    parite++;
                    nbEchanges++;
                    tmp = tab[j];
                    tab[j] = tab[j-1];
                    tab[j-1] = tmp;
                }
                else if (tab[j] == 0) {
                    continue;
                }
                else {
                    if (tab[j-1] > tab[j]) {
                        nbEchanges++;
                        tmp = tab[j - 1];
                        tab[j-1] = tab[j];
                        tab[j] = tmp;
                    }
                }
            }
        }
        res[0] = nbEchanges;
        res[1] = parite;
        return res;
    }

    /**
     * V&eacute;rifie la solvabilit&eacute; du plateau
     * @return true si le nombre d'&eacute;changes et la parite sont tous les 2 pairs ou impairs, false sinon
     */
    public boolean estSoluble(Case [][]tab) {
        return tri(tab)[0] % 2 == tri(tab)[1] % 2;
    }
}
