
public class TaquinLanceur {

    public static void main(String[] args) {
        new Jeu();

        //Test du parcours en largeur
        Configuration c = new Configuration(3,3); // taille a modif pour les test
        c.nouveauTableau();

        // 3x3 de profondeur 1 en 0.04s
        //int[][] t = {{1,2,3},{4,5,0},{7,8,6}};

        // 3x3 de profondeur 13 en 0.09s
        //int[][] t = {{5,2,3}, {1,7,0},{8,4,6}};

        // 3x3 de profondeur 20 en 0.4s
        //int[][] t = {{4,2,3},{5,1,7},{8,6,0}};

        // 3x3 de profondeur 31 en 0.9s
        //int[][] t = {{8,6,7},{2,5,4},{3,0,1}};
        int[][] t = {{6,4,7},{8,5,0},{3,2,1}};

        // 4x4 de profondeur 21 en 90s
        //int[][] t = {{2 , 6, 11, 3},{1 , 9 , 7 , 4},{5 , 10 , 12, 0},{13, 14, 15, 8}};

        /* Puzzle de X coups en Y sec (Taquin 4x4)
        16 en 1.873s
        17 en 3.992s
        18 en 7.661s
        19 en 20.936s
        20 en 41.538s
        21 en 93.632s
        Taille du marqueur : 11 399 742
         */
        // place du 0 a modif pour les test
        c.setX(1);
        c.setY(2);
        c.setTableau(t);
        c.afficher();
        System.out.println("### Algo ###");

        long timeStart;
        long timeStop;
        ParcoursLargeur pl = new ParcoursLargeur(c);
        timeStart = System.nanoTime();
        Configuration resolu = pl.parcoursEnLargeur();
        timeStop = System.nanoTime();

        resolu.afficher();
        System.out.println("Chemin : " + resolu.getChemin());
        System.out.println("Longueur du chemin : " + resolu.getChemin().length());
        System.out.println("Résolu en : " + ((timeStop-timeStart)/1000000)/1000.0 + "s");
    }
}
