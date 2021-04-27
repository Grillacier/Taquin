import java.lang.reflect.Type;
import java.util.*;

import org.graalvm.compiler.graph.Graph;
public class TaquinLanceur {


    public static void main(String[] args) {

        //Jeu jeu = new Jeu();

        //int[][] t = {{8,6,7},{2,5,4},{3,0,1}}; // 3x3
        //int[][] t = {{3,0,1},{2,5,4},{8,7,6}};
        //int[][] t = {{5,1,0,3}, {9,2,7,4}, {13,10,11,8}, {14,6,15,12}}; // 4x4
        //int[][] t = {{1,2,3,4}, {5,6,7,8}, {9,10,11,0}, {13,14,15,12}};
        //int[][] t = {{1,2,3,4}, {5,6,7,8}, {9,10,12,15}, {13,14,11,0}};
        //int[][] t = {{1,2,13,4}, {5,6,7,8}, {9,10,12,15}, {3,11,14,0}};
        //int[][] t = {{ 1, 2, 0, 3, 4}, { 6, 7,14, 9, 5}, {11,13, 8,19,10}, {16,12,18,24,15}, {21,17,22,23,20}}; // 5x5
        Configuration c = new Configuration(3,3); // taille a modif pour les test
        
        //c.setTableau(t);
        //c.initialisationXY();
        c.afficher();
        System.out.println("nombre d'échanges : " + c.triEchangeParite()[0]);
        System.out.println("parité du 0 : " + c.triEchangeParite()[1]);
        System.out.println("c est soluble : " + c.estSoluble());


        Algorithme ae = new Algorithme(c, new Aetoile(c));
        //Algorithme pl = new Algorithme(c, new ParcoursLargeur(c));
        //Algorithme d = new Algorithme(c, new Dijkstra(c));

        Graphique g=new Graphique();

    }
}
