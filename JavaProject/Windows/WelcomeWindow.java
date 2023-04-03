import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.BorderLayout;
//for helper
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeWindow extends JPanel{

  JButton button1A;
  JButton button2A;
    JButton button2B;
    JButton button2C;


  public WelcomeWindow(){

    this.setBounds(192,192,60,30);

    this.setBackground(Color.BLUE);
  }
}