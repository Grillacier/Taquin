import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.*;
import javax.imageio.*;
import java.awt.event.KeyEvent;

public class Accueil extends JFrame{
    private Joueur joueur;
    private JPanel accueilHaut, accueilBas, reglesDuJeu;
    private JButton jouer, charger, regles, validerPseudo, retourAccueil;
    private JTextField pseudo;

    public Accueil(){
        joueur = new Joueur();
        joueur.lire();
        this.setTitle("Taquin 4");
        this.setSize(1250, 800);
        this.setResizable(false);
        getContentPane().setLayout(new GridLayout(2,1));

        jouer = new JButton("Jouer");
        charger = new JButton("Charger");
        regles = new JButton("Règles");
        validerPseudo = new JButton("Valider");
        retourAccueil = new JButton("Retour à l'accueil");

        panelAcceuil();

        /* Action Listener */

        regles.addActionListener((event)->{
                getContentPane().setLayout(new GridLayout(1,1));
                System.out.println("help");
                getContentPane().remove(accueilBas);
                getContentPane().remove(accueilHaut);
                getContentPane().add(panelRegles());
                getContentPane().validate();
                getContentPane().repaint();

        });

        charger.addActionListener(
            (ActionEvent e)->{
              System.out.println("charge");

        });

        jouer.addActionListener(
            (ActionEvent e)->{
                    System.out.println("jouer");
        });

        validerPseudo.addActionListener((event) ->{
            joueur.setNom(pseudo.getText());
            joueur.sauvegarder();
            System.out.println("Valider pressed");
        });

        retourAccueil.addActionListener((event)->{
            getContentPane().setLayout(new GridLayout(2,1));
            getContentPane().remove(reglesDuJeu);
            panelAcceuil();
            getContentPane().validate();
            getContentPane().repaint();


                });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void panelAcceuil(){
        JPanel accueil = new JPanel();

        accueilHaut = new JPanel();
        accueilBas = new JPanel();

        accueilHaut.setBackground(Color.WHITE);
        accueilBas.setBackground(Color.GRAY);

        accueilHaut.setLayout(null);
        accueilBas.setLayout(null);

        getContentPane().add(accueilHaut);
        getContentPane().add(accueilBas);

        // Panneau HAUT
        JLabel titre = new JLabel();
        titre.setText("Bienvenue sur le jeu du Taquin !");
        titre.setFont(new Font("New Times Roman", Font.BOLD, 40));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setVerticalAlignment(SwingConstants.TOP);
        titre.setBounds(150,20,1000,50);
        accueilHaut.add(titre);

        JLabel id=new JLabel("Votre pseudo :");
        id.setBounds(450,100,300,50);
        accueilHaut.add(id);

        pseudo = new JTextField(joueur.getNom());
        pseudo.setBounds(480,150,300,50);
        accueilHaut.add(pseudo);

        accueilHaut.add(validerPseudo);
        validerPseudo.setBounds(790,150,100,50);

        //Panneau BAS
        accueilBas.add(jouer);
        accueilBas.add(charger);
        accueilBas.add(regles);
        jouer.setBounds(150,100,300,90);
        charger.setBounds(480,100,300,90);
        regles.setBounds(810,100,300,90);
    }


  /*  public JPanel getAcceuilPanelHaut(){

      // JPANEL
      JPanel panneauHaut = new JPanel();
      panneauHaut.setBackground(Color.WHITE);
      panneauHaut.setLayout(null);

      // Panneau HAUT
      JLabel titre = new JLabel();
      titre.setText("Bienvenue sur le jeu du Taquin !");
      titre.setFont(new Font("New Times Roman", Font.BOLD, 40));
      titre.setHorizontalAlignment(SwingConstants.CENTER);
      titre.setVerticalAlignment(SwingConstants.TOP);
      titre.setBounds(150,20,1000,50);
      panneauHaut.add(titre);

      JLabel id=new JLabel("Votre pseudo :");
      id.setBounds(450,100,300,50);
      panneauHaut.add(id);

      JTextField pseudo = new JTextField("");
      pseudo.setBounds(480,150,300,50);
      //pseudo.setBackground(Color.YELLOW);
      //pseudo.setForeground(Color.GRAY);
      panneauHaut.add(pseudo);

      JButton v=new JButton("Valider");
      panneauHaut.add(v);
      v.setBounds(790,150,100,50);
      return panneauHaut;
   }

   public JPanel getAccueilPanelBas(){
     // JPANEL
     JPanel panneauBas = new JPanel();
     panneauBas.setBackground(Color.GRAY);
     panneauBas.setLayout(null);

     //Panneau BAS
     JButton j=new JButton("Jouer/Play");
     JButton c=new JButton("Charger/Load");
     JButton r=new JButton("Règles du jeu");
     panneauBas.add(j);
     panneauBas.add(c);
     panneauBas.add(r);
     j.setBounds(150,100,300,90);
     c.setBounds(480,100,300,90);
     r.setBounds(810,100,300,90);

     r.addActionListener(
         (ActionEvent e)->{
             getContentPane().setLayout(new GridLayout(1,1));
             System.out.println("help");
             getContentPane().remove(panneauBas);
             getContentPane().remove(panneauHaut);
             getContentPane().add(getHelpPanel());
             getContentPane().validate();
             getContentPane().repaint();

     });
     c.addActionListener(
         (ActionEvent e)->{
           System.out.println("charge");

     });
     j.addActionListener(
         (ActionEvent e)->{
                 System.out.println("jouer");
     });
      return panneauBas;
   } */

    public JPanel panelRegles(){
        reglesDuJeu = new JPanel();
        reglesDuJeu.setLayout(null);
        reglesDuJeu.add(retourAccueil);
        retourAccueil.setBounds(100,500,1050,150);
        JLabel texte=new JLabel("<html>Résolution manuelle :<br>Afin de résoudre le célèbre jeu du taquin, il faut déplacer les cases verticalement et horizontalement sur la case vide.<br>Une fois avoir déplacer les cases nécéssaires et qu'elles sont dans l'ordre croissant, vous avez gagnez !<br><br>Résolution automatique :<br>Sélectionner la taille de la configuation de taquin souhaité.<br>Choisir l'algorithme de votre choix.<br>Cliquez sur Lancer !<br></html>");
        JLabel continuation=new JLabel("Profitez bien du jeu !!!");
        texte.setFont(new Font("New Times Roman", Font.BOLD, 15));
        texte.setBounds(50,50,1150,400);
        continuation.setBounds(500,150,1150,400);
        continuation.setFont(new Font("New Times Roman", Font.BOLD, 17));
        reglesDuJeu.add(texte);
        reglesDuJeu.add(continuation);
        return reglesDuJeu;



    }
}
