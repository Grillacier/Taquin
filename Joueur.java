import java.io.*;
import java.util.Scanner;

public class Joueur {
    private String nom;
    private int age;
    private Scanner scanReponse;
    private boolean robot;

    public Joueur() {
        this.nom = "Anonyme"; // Pseudo par défaut
        this.scanReponse = new Scanner(System.in);
        this.robot = false;
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

    public String demanderStr(String q) {
        System.out.println(q);
        return scanReponse.next();
    }

    public void finir() {
        this.scanReponse.close();
    }
}
