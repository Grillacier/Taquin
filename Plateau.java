//import game_test.Case;

public class Plateau {
    public Case[][] plateau; // double tableau
    public int hauteur, largeur; // taille du tableau

    public Plateau(int h, int l){
        this.hauteur = h;
        this.largeur = l;
        this.plateau = tabNombreAleatoire(h, l);
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
		// ajoute dans un premier temps dans le tableau tabTaquin des chiffres compris entre 0 et (ligne * colonne)-1
		for(int i=0; i < ligne; i++) {
			for (int j=0; j<colonne; j++) {
				tabTaquin[i][j] = new Case(x);
				x++;
					
				}
			}
		melangerTab(tabTaquin); //  mélange le tableau aléatoirement
		
		return tabTaquin; 
	}

    public void afficher(){
        char c = 'A';
        int size;
        System.out.print("   "); // espaces de la premiere ligne
        for(int i=0; i<this.largeur; i++) // affiche la premiere ligne
            System.out.print(i + "  ");
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
    public boolean jeuGagne(Case[][] tab){ //fonction qui évalue si le jeu est terminé (gagné) ou pas
        if(tab[tab.length-1][tab[0].length-1].getNumero()!=0){
            return false;
        }

        int k=1;
        for(int i=0; i<tab.length-1; i++){
            for(int j=0; j<tab[i].length; j++){
                if(tab[i][j].getNumero()!=k){
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
    public void mouvement(int cibleh, int ciblel){
        Case cible = this.plateau[cibleh][ciblel];
        if(cibleh + 1 <hauteur){
            if(this.plateau[cibleh+1][ciblel].getNumero() == 0){   //Je suppose que le case vide est la case 0
                this.plateau[cibleh][ciblel] = this.plateau[cibleh+1][ciblel];
                this.plateau[cibleh+1][ciblel] = cible;
                return;
            }
        }
        if(cibleh - 1 >-1){
            if(this.plateau[cibleh-1][ciblel].getNumero() == 0){
                this.plateau[cibleh][ciblel] = this.plateau[cibleh-1][ciblel];
                this.plateau[cibleh-1][ciblel] = cible;
                return;
            }

        }
        if(ciblel + 1 <largeur){
            if(this.plateau[cibleh][ciblel+1].getNumero() == 0){
                this.plateau[cibleh][ciblel] = this.plateau[cibleh][ciblel+1];
                this.plateau[cibleh][ciblel+1] = cible;
                return;
            }

        }
        if(ciblel - 1 >-1){
            if(this.plateau[cibleh][ciblel-1].getNumero() == 0){
                this.plateau[cibleh][ciblel] = this.plateau[cibleh][ciblel-1];
                this.plateau[cibleh][ciblel-1] = cible;
                return;
            }

        }
        System.out.println("Impossible de deplacer la case cible x: "+ciblel+"   y: "+cibleh);
    }


    /**
     * Convertit un double tableau de Cases en un tableau d'entiers
     * @return un tableau d'entiers contenant les num&eacute;ros des Cases sans en changer l'ordre
     */
    public int[] conversion() {
        int[] tab = new int[this.plateau.length*this.plateau[0].length];
        int k = 0;
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; j++) {
                tab[k] = this.plateau[i][j].getNumero();
                k++;
            }
        }
        return tab;
    }

    /**
     * Tri un tableau d'entiers dans l'ordre croissant avec le 0 a la fin
     * @param p Tableau tableau de tableaux de Cases dont on va trier les cases
     * @return le nombre d'&eacute;changes et de d&eacute;placements du 0
     */
    private int[] tri(Case[][] p) {
        int[] tab = conversion(); // plateau convertit en tableau d'entiers
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
    public boolean estSoluble() {
        return tri(this.plateau)[0] % 2 == tri(this.plateau)[1] % 2;
    }
}
