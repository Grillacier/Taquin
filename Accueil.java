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
    public Accueil(){
        this.setTitle("Projet Taquin 4");
        this.setSize(1250, 800);
        this.setResizable(false);

        // JFRAME
        getContentPane().setLayout(new GridLayout(2,1));

        // JPANEL
        JPanel panneauHaut = new JPanel();
        JPanel panneauBas = new JPanel();

        panneauHaut.setBackground(Color.WHITE);
        panneauBas.setBackground(Color.GRAY);

        panneauHaut.setLayout(null);
        panneauBas.setLayout(null);

        getContentPane().add(panneauHaut);
        getContentPane().add(panneauBas);


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

        /*pseudo.addActionListener((event) ->{

            pseudo.setText("");
            pseudo.repaint();
            pseudo.revalidate();
        });*/

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
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

    public JPanel getHelpPanel(){
      JPanel help = new JPanel();
      help.setLayout(null);
      JButton toAcceuil = new JButton("Retour a la page d accueil");
      help.add(toAcceuil);
      toAcceuil.setBounds(100,500,1050,150);
      JLabel regle = new JLabel("Mettre help");
      help.add(regle);
      regle.setBounds(50,50,1150,400);

      toAcceuil.addActionListener(
          (ActionEvent e)->{
                  System.out.println("retour");
                   getContentPane().setLayout(new GridLayout(2,1));
                   getContentPane().remove(help);

                           // JPANEL
                           JPanel panneauHaut = new JPanel();
                           JPanel panneauBas = new JPanel();

                           panneauHaut.setBackground(Color.WHITE);
                           panneauBas.setBackground(Color.GRAY);

                           panneauHaut.setLayout(null);
                           panneauBas.setLayout(null);

                           getContentPane().add(panneauHaut);
                           getContentPane().add(panneauBas);


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
                         (ActionEvent a)->{
                               getContentPane().setLayout(new GridLayout(1,1));
                               System.out.println("help");
                               getContentPane().remove(panneauBas);
                               getContentPane().remove(panneauHaut);
                               getContentPane().add(getHelpPanel());
                               getContentPane().validate();                                 getContentPane().repaint();

                           });
                 c.addActionListener(
                       (ActionEvent a)->{
                             System.out.println("charge");

                           });
                 j.addActionListener(
                         (ActionEvent a)->{
                                   System.out.println("jouer");
                     });




                   getContentPane().validate();
                   getContentPane().repaint();


      });

      return help;



    }
}
