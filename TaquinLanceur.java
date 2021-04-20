import java.lang.reflect.Type;
import java.util.*;
public class TaquinLanceur {


    public static void main(String[] args) {
        new Jeu();

        // Test des algorithmes

            // 3x3 de profondeur 1 en 0.04s
        //int[][] t = {{1,2,3},{4,5,0},{7,8,6}};

            // 3x3 de profondeur 13 en 0.09s
        //int[][] t = {{5,2,3}, {1,7,0},{8,4,6}};

            // 3x3 de profondeur 20 en 0.4s
        //int[][] t = {{4,2,3},{5,1,7},{8,6,0}};

            // 3x3 de profondeur 31 en 0.9s
        //int[][] t = {{8,6,7},{2,5,4},{3,0,1}};
        //int[][] t = {{6,4,7},{8,5,0},{3,2,1}};

            // 4x4 de profondeur 21 en 90s
        //int[][] t = {{2 , 6, 11, 3},{1 , 9 , 7 , 4},{5 , 10 , 12, 0},{13, 14, 15, 8}};

        Configuration c = new Configuration(3,3); // taille a modif pour les test
        //Algorithme pl = new Algorithme(c, new ParcoursLargeur(c));
        //Algorithme d = new Algorithme(c, new Dijkstra(c));
        Algorithme ae = new Algorithme(c, new Aetoile(c));
        //Algorithme test = new Algorithme(c, new Joueur());

    }
}
