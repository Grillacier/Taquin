import java.util.*;

public class ParcoursLargeur{

    private Plateau p;
    private Queue q;
    private LinkedList marqueur;


    public ParcoursLargeur(){
        this.p=new Plateau(3,3);
      //  this.p = new Plateau();
        this.q=new Queue(this.p);
        this.marqueur = new LinkedList();



    }

    public boolean resolu(){
      return this.p.jeuGagne();
    }

    public boolean memePlateau(Plateau a){
      boolean res = true;
      int iterator =0;
      for(int w = 0; w<this.marqueur.size();w++){
        res = true;
        Plateau b = (Plateau)this.marqueur.get(w);

        for(int i = 0; i<a.hauteur;i++){
          for(int j = 0; j<a.largeur;j++){
            if(a.plateau[i][j].getNumero() != b.plateau[i][j].getNumero()){
                res = false;
            }
          }
        }
        if(res == true){
          return true;
        }
      }
     return false;
    }
    public void parcours(){
      this.marqueur.add(this.p);
      Plateau up = new Plateau(this.p.getCompteur()+1,this.p);
      up.mouvement("haut");
      if(memePlateau(up)==false){

          q.addPlateau(up);
      }
      Plateau down = new Plateau(this.p.getCompteur()+1,this.p);
      up.mouvement("bas");
      if(memePlateau(down)==false){
          q.addPlateau(down);
      }
      Plateau right = new Plateau(this.p.getCompteur()+1,this.p);
      up.mouvement("droite");
      if(memePlateau(right)==false){
          q.addPlateau(right);
      }
      Plateau left = new Plateau(this.p.getCompteur()+1,this.p);
      up.mouvement("gauche");
      if(memePlateau(left)==false){
          q.addPlateau(left);
      }

    }
    public boolean setNext(){
      if(this.q.hasNext()){
        this.p = this.q.extract();
        return true;
    }
    return false;
}

}
