import java.util.*;

public class ParcoursLargeur{

    private Configuration configuration;
    private LinkedList<Configuration> marqueur; // nouveau nom : visite

    public ParcoursLargeur(Configuration c){
        this.configuration = c;
        this.marqueur = new LinkedList<>();
    }

    public boolean resolu(Configuration x){
        return x.jeuGagne();
    }

    public boolean plateauDifferent(Configuration a, String dir){
        Configuration x, y;
        boolean estDifferent;
        y = new Configuration(a.getHauteur(), a.getLargeur());
        y.copier(a);
        y.mouvement(dir);

        for(int k = 0; k<this.marqueur.size(); k++){
            estDifferent = false;
            x = this.marqueur.get(k);
            for(int i = 0; i<y.getHauteur(); i++){
                for(int j = 0; j<y.getLargeur();j++){
                    if(y.getTableau()[i][j] != x.getTableau()[i][j]){
                        estDifferent = true;
                    }
                }
            }
            if(!estDifferent){
                return false;
            }
        }
        return true;
    }

    public Configuration parcours(){
        LinkedList<Configuration> file = new LinkedList<Configuration>();
        marqueur.add(this.configuration);
        file.add(this.configuration);
        int x = 0;
        while(!file.isEmpty()){
            System.out.println("Taille de la file (avant poll) : " + file.size());
            Configuration a = file.poll();
            System.out.println("Taille de la file (apres poll) : " + file.size());
            a.afficher();
            System.out.println("Taille du marqueur : " + marqueur.size());
            if (a.jeuGagne()) {
                return a;
            }
            if(plateauDifferent(a, "haut")){
                Configuration y = new Configuration(a);
                y.mouvement("haut");
                marqueur.add(y);
                file.addLast(y);
            }if(plateauDifferent(a, "bas")){
                Configuration y = new Configuration(a);
                y.mouvement("bas");
                marqueur.add(y);
                file.addLast(y);
            }if(plateauDifferent(a, "droite")){
                Configuration y = new Configuration(a);
                y.mouvement("droite");
                marqueur.add(y);
                file.addLast(y);
            }if(plateauDifferent(a, "gauche")){
                Configuration y = new Configuration(a);
                y.mouvement("gauche");
                marqueur.add(y);
                file.addLast(y);
            }
        }
        return null;
    }



}
