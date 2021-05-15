import java.io.*;

public class Joueur implements Serializable{
    private String nom;
    private boolean robot;
    private static final long serialVersionUID = 5030499181306931978L;

    public Joueur() {
        this.nom = "Anonyme"; // Pseudo par défaut
        this.robot = false;
    }

    /**
     * Fonction de sauvegarde
     */

    public void sauvegarder(){
        try {
            File f = new File("joueur.dat");
            if(f.delete()){ // permet de mettre a jour les informations
                FileOutputStream fichier = new FileOutputStream("joueur.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fichier);
                oos.writeObject(this);
                oos.flush();
                oos.close();
            }else{
                System.out.println("Erreur lors de la sauvegarde");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void lire(){
        try{
            File f = new File("joueur.dat");
            if(f.createNewFile()){
                this.sauvegarder();
            }
            FileInputStream fichier = new FileInputStream("joueur.dat");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            Joueur j = (Joueur) ois.readObject();
            this.nom = j.nom;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean getRobot() {
        return robot;
    }

    public void setRobot(boolean robot) {
        this.robot = robot;
    }
}