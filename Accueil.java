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
        this.setTitle("Bienvenue ! Welcome !");
        this.setSize(1250, 800);
        //Titre de la page
        JLabel titre=new JLabel();
        titre.setText("Bienvenue sur le jeu du Taquin !");
        titre.setFont(new Font("New Times Roman", Font.BOLD, 40));
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setVerticalAlignment(SwingConstants.TOP);
        this.getContentPane().add(titre);

        //Initialisation d'un JPanel
        JPanel panneau=new JPanel();
        panneau.setBackground(Color.GRAY);
        panneau.setLayout(new BorderLayout());
        getContentPane().setLayout(new GridLayout(2,1));
        getContentPane().add(panneau);

        //Initialisation des boutons de sélection

        panneau.setLayout(null);
        JButton j=new JButton("Jouer/Play");
        JButton c=new JButton("Charger/Load");
        JButton r=new JButton("Règles du jeu");
        panneau.add(j);
        panneau.add(c);
        panneau.add(r);
        j.setBounds(100,20,300,90);
        c.setBounds(430,20,300,90);
        r.setBounds(760,20,300,90);

        j.addActionListener((event) ->{System.out.println("Jouer pressed");});
        c.addActionListener((event) ->{System.out.println("Charger pressed");});
        r.addActionListener((event) ->{System.out.println("Regle pressed");});


        JLabel id=new JLabel();
        id.setText("Entrez votre pseudo :");
        id.setBounds(250,200,300,50);
        panneau.add(id);

        JTextField player_name=new JTextField();
        player_name.setBounds(430,200,300,50);
        panneau.add(player_name);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
