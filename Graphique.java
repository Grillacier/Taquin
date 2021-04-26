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

public class Graphique extends JFrame{
  Joueur joueur;
  Jeu modele;

public class EntreListener extends KeyAdapter implements KeyListener{

    JTextField input;
    JLabel output;


    public EntreListener(JTextField l,JLabel t){
      this.input = l;
      this.output = t;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){

          System.out.println("ENTRER PRESSED");
          String command ="USER: "+input.getText();
          this.output.setText(command);
          this.input.setText("");

        }
      }

      public void keyReleased(KeyEvent e){

      }
      public void keyTyped(KeyEvent e){

      }

}
  public Graphique(){
    this.setTitle("Taquin");
    this.setSize(1250, 800);
    this.setLocationRelativeTo(null);

    //Instanciation Panel
    JPanel interaction = new JPanel();
    JPanel module = new JPanel();
    interaction.setBounds(0,0,250,800);
    module.setBounds(250,0,1000,800);
    this.setResizable(false);
    interaction.setPreferredSize(new Dimension(250, 800));
    module.setPreferredSize(new Dimension(1000,800));

    //Définition de sa couleur de fond
    interaction.setBackground(Color.ORANGE);
    module.setBackground(Color.BLUE);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(null);
    this.getContentPane().add(interaction);
    this.getContentPane().add(module);

    //les boutons
    //interaction.setLayout(new BoxLayout(interaction,BoxLayout.Y_AXIS));
    interaction.setLayout(null);

    JButton p=new JButton("Jouer/Play");
    JButton h=new JButton("Charger");
    JButton r=new JButton("Réinitialiser/Reset");
    JLabel terminal = new JLabel("1+1, ca fait cb?");
    JLabel userGUI = new JLabel("User");
    JTextField entre = new JTextField();

    interaction.add(p);
    interaction.add(h);
    interaction.add(r);

    interaction.add(entre);

    interaction.add(terminal);
    interaction.add(userGUI);

    terminal.setOpaque(true);
    terminal.setForeground(Color.red);
    terminal.setBackground(Color.black);
    userGUI.setOpaque(true);
    userGUI.setForeground(Color.white);
    userGUI.setBackground(Color.black);

    p.setBounds(0,0,250,100);
    h.setBounds(0,100,250,100);
    r.setBounds(0,200,250,100);
    entre.setBounds(0,710,250,30);
    terminal.setBounds(0,300,250,205);
    userGUI.setBounds(0,505,250,205);

    p.setMaximumSize(new Dimension(250,100));
    p.setMinimumSize(new Dimension(250,100));
    p.setPreferredSize(new Dimension(250,100));

    h.setMaximumSize(new Dimension(250,100));
    h.setMinimumSize(new Dimension(250,100));
    h.setPreferredSize(new Dimension(250,100));

    r.setMaximumSize(new Dimension(250,100));
    r.setMinimumSize(new Dimension(250,100));
    r.setPreferredSize(new Dimension(250,100));


    entre.setMaximumSize(new Dimension(250,70));
    entre.setMinimumSize(new Dimension(250,70));
    entre.setPreferredSize(new Dimension(250,70));
    entre.addKeyListener(new EntreListener(entre,userGUI));

      this.setVisible(true);

 }



}
