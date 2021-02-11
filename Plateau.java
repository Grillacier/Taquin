
public class Plateau {
    public Case[][] plateau; // double tableau
    public int hauteur, largeur; // taille du tableau

    public Plateau(int h, int l){
        this.hauteur = h;
        this.largeur = l;
        this.plateau = new Case[this.hauteur][this.largeur];
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
    public boolean jeuGagner(Case[][] tab){ // fonction qui évalue si le jeu est terminé (gagné) ou pas
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


    /**
     * Convertit un double tableau de Cases en un tableau d'entiers
     * @param p le double tableau de Cases
     * @return un tableau d'entiers contenant les num&eacute;ros des Cases sans en changer l'ordre
     */
    private int[] conversion(Case[][] p) {
        int[] tab = new int[p.length*p[0].length];
        int k = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                tab[k] = p[i][j].getNumero();
                k++;
            }
        }
        return tab;
    }

    /**
     * Tri un tableau d'entiers dans l'ordre croissant
     * @param p Tableau tableau de tableaux de Cases dont on va trier les cases
     * @return le nombre d'&eacute;changes et de d&eacute;placements du 0
     **/
    private int[] tri(Case[][] p) {
        int[] tab = conversion(p); // plateau convertit en tableau d'entiers
        int[] res = new int[2];
        int nbEchanges = 0; // nombre de deplacements total des Cases
        int parite = 0; // nombre de deplacements du 0
        int tmp = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 1; j < tab.length-i; j++) {
                if (tab[j-1] > tab[j]) {
                    if (tab[j] == 0)
                        parite++;
                    nbEchanges++;
                    tmp = tab[j-1];
                    tab[j-1] = tab[j];
                    tab[j] = tmp;
                }
            }
        }
        res[0] = nbEchanges;
        res[1] = parite;
        return res;
    }

    /**
     * Tri un tableau d'entiers dans l'ordre croissant avec le 0 a la fin
     * @param p Tableau tableau de tableaux de Cases dont on va trier les cases
     * @return le nombre d'&eacute;changes et de d&eacute;placements du 0
     **/
    private int[] tri2(Case[][] p) {
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
                    if (tab[j - 1] > tab[j]) {
                        nbEchanges++;
                        tmp = tab[j - 1];
                        tab[j - 1] = tab[j];
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
     * Verifie la solvabilite du plateau en parametre
     * @param p Tableau de tableaux de Cases
     * @return true si le nombre d'&eacute;changes et la parite sont tous les 2 pairs ou impairs, false sinon
     */
    public boolean estSoluble(Case[][] p) {
        /*
        System.out.println("echanges : " + tri(p)[0]);
        System.out.println("parite : " + tri(p)[1]);
        System.out.println("echanges : " + tri2(p)[0]);
        System.out.println("parite : " + tri2(p)[1]);
        */
        return tri(p)[0] % 2 == tri(p)[1] % 2 || tri2(p)[0] % 2 == tri2(p)[1] % 2;
    }
}
