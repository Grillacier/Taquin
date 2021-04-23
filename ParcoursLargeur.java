import java.util.*;
import java.util.Queue;

public class ParcoursLargeur{
    private Configuration configuration;
    private HashSet<String> marqueur;
    private Queue<Configuration> file;

    // Constructeur prenant la configuration de depart

    /** Contructeur de l'algorithme du parcours en largeur
      * @param c
      *    Configuration initiale
    */
    public ParcoursLargeur(Configuration c){
        this.configuration = c;
        this.marqueur = new HashSet<>();
        this.file = new LinkedList<>();
    }

    // Parcours en largeur qui marque les config deja visites et ajoute a la file les config a visister

    /** Algorithme du parcours en largeur
      *
      */
    public Configuration parcoursEnLargeur(){
        marqueur.add(this.configuration.tableauEnString());
        file.add(this.configuration);
        while(!file.isEmpty()){
            Configuration a = file.poll();
            if (a.jeuGagne()) {
                System.out.println("Taille du marqueur : " + marqueur.size());
                return a;
            }
            // Verifie chaque deplacement du taquin a
            for(int i=0; i<a.getDeplacements().length; i++){
                Configuration tmp = new Configuration(a);
                tmp.mouvement(a.getDeplacements()[i]);
                if(this.marqueur.add(tmp.tableauEnString())){
                    file.add(tmp);
                }
            }
        }
        return null;
    }



}
