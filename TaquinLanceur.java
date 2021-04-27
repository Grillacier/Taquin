import java.lang.reflect.Type;
import java.util.*;

import org.graalvm.compiler.graph.Graph;
public class TaquinLanceur {


    public static void main(String[] args) {

        Jeu jeu = new Jeu();

        //int[][] t = {{8,6,7},{2,5,4},{3,0,1}}; // 3x3
        //int[][] t = {{5,1,0,3}, {9,2,7,4}, {13,10,11,8}, {14,6,15,12}}; // 4x4
        //int[][] t = {{ 1, 2, 0, 3, 4}, { 6, 7,14, 9, 5}, {11,13, 8,19,10}, {16,12,18,24,15}, {21,17,22,23,20}}; // 5x5
        //Configuration c = new Configuration(3,3); // taille a modif pour les test
        
        /*.setTableau(t);
        c.initialisationXY();
        System.out.println("c est soluble : " + c.estSoluble());*/
        //c.afficher();


        //Algorithme ae = new Algorithme(c, new Aetoile(c));
        //Algorithme pl = new Algorithme(c, new ParcoursLargeur(c));
        //Algorithme d = new Algorithme(c, new Dijkstra(c));

        Graphique g=new Graphique();

    }
}
