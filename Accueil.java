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

    public Accueil(){
        joueur = new Joueur();
        joueur.lire();
        this.setTitle("Taquin 4");
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

        JTextField pseudo = new JTextField(joueur.getNom());
        pseudo.setBounds(480,150,300,50);
        //pseudo.setBackground(Color.YELLOW);
        //pseudo.setForeground(Color.GRAY);
        panneauHaut.add(pseudo);

        JButton v=new JButton("Valider");
        panneauHaut.add(v);
        v.setBounds(790,150,100,50);

        //Panneau BAS
        JButton j=new JButton("Jouer");
        JButton c=new JButton("Charger");
        JButton r=new JButton("Règles");
        panneauBas.add(j);
        panneauBas.add(c);
        panneauBas.add(r); 
        j.setBounds(150,100,300,90);
        c.setBounds(480,100,300,90);
        r.setBounds(810,100,300,90);



        j.addActionListener((event) ->{System.out.println("Jouer pressed");});
        c.addActionListener((event) ->{System.out.println("Charger pressed");});
        r.addActionListener((event) ->{System.out.println("Regle pressed");});
        v.addActionListener((event) ->{
            joueur.setNom(pseudo.getText());
            joueur.sauvegarder();
            System.out.println("Valider pressed");
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}