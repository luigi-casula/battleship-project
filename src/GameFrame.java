import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameFrame extends JFrame {

    private Ship[] ships_p1 = new Ship[5];
    private Ship[] ships_p2 = new Ship[5];

    public GameFrame() throws IOException {
        super("Battleship");
        customizeFrame();
        shipsManager();
    }

    public void shipsManager() throws IOException {
            BufferedReader br1 = new BufferedReader(new FileReader("ships1.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("ships2.txt"));
            String line;
            int i = 0;
            int j = 0;
            while ((line = br1.readLine()) != null) {
                String[] data = line.split(",");
                int pos_x = Integer.parseInt(data[1]);
                int pos_y = Integer.parseInt(data[2]);
                ships_p1[i] = new Ship(Integer.parseInt(data[0]), pos_x, pos_y);
                i++;
            }
            while ((line = br2.readLine()) != null) {
                String[] data = line.split(",");
                int pos_x = Integer.parseInt(data[1]);
                int pos_y = Integer.parseInt(data[2]);
                ships_p2[j] = new Ship(Integer.parseInt(data[0]), pos_x, pos_y);
                j++;
            }
            br1.close();
            br2.close();

            for (int k = 0; k < 5; k++) {
                System.out.println(ships_p1[k].getLength() + " " + ships_p1[k].getPos_x() + " " + ships_p1[k].getPos_y());
            }
            for (int k = 0; k < 5; k++) {
                System.out.println(ships_p2[k].getLength() + " " + ships_p2[k].getPos_x() + " " + ships_p2[k].getPos_y());
            }
    }



    public void customizeFrame() {
        Container c = getContentPane();
        setBounds(100,100,500,500);
        setVisible(true);
        setResizable(false);

    }



}
