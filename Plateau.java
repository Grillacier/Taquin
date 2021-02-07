
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

    public boolean jeuGagner(){ //fonction qui évalue si le jeu est terminé (gagné) ou pas
        Case[][] ptrie=new Case[][]{
            {new Case(1), new Case(2), new Case(3), new Case(4)},
            {new Case(5), new Case(6), new Case(7), new Case(8)},
            {new Case(9), new Case(10), new Case(11), new Case(12)},
            {new Case(13), new Case(14), new Case(15), new Case(0)}};
        for(int i=0; i<plateau.length; i++){
            for(int j=0; plateau[i].length; j++){
                if(plateau[i][j].getNumero()!=ptrie[i][j].getNumero()){
                    return false;
                }
            }
        }
        return true;
    }
}
