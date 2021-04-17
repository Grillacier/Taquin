import java.util.*;
public class TaquinLanceur {

    public static void main(String[] args) {
        new Jeu();

        //Test du parcours en largeur
        Configuration c = new Configuration(3,3); // taille a modif pour les test
        //Configuration d = new Configuration(3,3);
        //Configuration e = new Configuration(3,3);
        Configuration w = c.tableauFinal();
       // ArrayList<Configuration> a = new ArrayList<Configuration>();
       /* a.add(d);
        a.add(e);*/
        
        c.afficher();
        //System.out.println(fdp.toString());
        long timeStart;
        long timeStop;
        System.out.println("Dijkstra :");
        Dijkstra b = new Dijkstra (c);
        timeStart = System.nanoTime();
        Configuration resolu = b.dijkstra();
        timeStop = System.nanoTime();

        resolu.afficher();
        System.out.println("Chemin : " + resolu.getChemin());
        System.out.println("Longueur du chemin : " + resolu.getChemin().length());
        System.out.println("Résolu en : " + ((timeStop-timeStart)/1000000)/1000.0 + "s");
        System.out.println();

       // System.out.println(c.distance(c));
      // System.out.println();
       // b.dijkstra(c);
        //c.afficher();
        //System.out.println();
        //d.afficher();
        //System.out.println(c.distance(d));
        //System.out.println(d.distance(d));
        //c.nouveauTab;leau();

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
      /*  c.setX(1);
        c.setY(2);
        c.setTableau(t);
        c.afficher();
        System.out.println("### Algo ###");*/

       /* long timeStart;
        long timeStop;*/
        System.out.println("Parcours en largeur : ");
        ParcoursLargeur pl = new ParcoursLargeur(c);
        timeStart = System.nanoTime();
        Configuration res = pl.parcoursEnLargeur();
        timeStop = System.nanoTime();

        resolu.afficher();
        System.out.println("Chemin : " + res.getChemin());
        System.out.println("Longueur du chemin : " + res.getChemin().length());
        System.out.println("Résolu en : " + ((timeStop-timeStart)/1000000)/1000.0 + "s");

       /* Dijkstra dj = new Dijkstra(c);
        dj.afficheChemin(dj.dijkstra());*/
    }
}
