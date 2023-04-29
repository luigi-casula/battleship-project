import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PlayingFrame extends JFrame {


    public PlayingFrame() throws IOException {
        super("Battleship - Playing");
        customizeFrame();
    }

    public void customizeFrame() throws IOException{
        Container c = getContentPane(); //container
        c.setLayout(new FlowLayout()); //definizione del layout manager

        int width1 = 234;
        int height1 = 84;
        BufferedImage ship1img = ImageIO.read(new File("src/images/ship model 1.png"));
        Image scaledImage1 = ship1img.getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
        JLabel ship1 = new JLabel(new ImageIcon(scaledImage1));
        c.add(ship1);

        int width2 = 274;
        int height2 = 70;
        BufferedImage ship2img = ImageIO.read(new File("src/images/ship model 2.png"));
        Image scaledImage2 = ship2img.getScaledInstance(width2, height2, Image.SCALE_SMOOTH);
        JLabel ship2 = new JLabel(new ImageIcon(scaledImage2));
        c.add(ship2);

        /*int width1 = 234;
        int height1 = 84;
        BufferedImage ship1img = ImageIO.read(new File("src/images/ship model 1.png"));
        Image scaledImage1 = ship1img.getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
        JLabel ship1 = new JLabel(new ImageIcon(scaledImage1));
        c.add(ship1);*/


        setVisible(true);
        setBounds(100,100, 300, 300);
    }
}