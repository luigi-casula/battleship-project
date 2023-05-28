import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class GameFrame extends JFrame implements MouseListener, WindowListener {

    JLabel p1_l = new JLabel("");
    JLabel p2_l = new JLabel("");

    JPanel player_p = new JPanel(); //pannello contenente il nome del giocatore
    JPanel grid = new JPanel(); //pannello contenente la griglia


    public GameFrame(String name1, String name2) throws IOException {
        super("Battleship");
        p1_l.setText("Turno di " + name1);
        p2_l.setText("Turno di " + name2);
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
            Globals.ships_p1[i] = new Ship(Integer.parseInt(data[0]), pos_x, pos_y);
            i++;
        }
        while ((line = br2.readLine()) != null) {
            String[] data = line.split(",");
            int pos_x = Integer.parseInt(data[1]);
            int pos_y = Integer.parseInt(data[2]);
            Globals.ships_p2[j] = new Ship(Integer.parseInt(data[0]), pos_x, pos_y);
            j++;
        }
        br1.close();
        br2.close();

        for (int k = 0; k < 5; k++) {
            System.out.println(Globals.ships_p1[k].getLength() + " " + Globals.ships_p1[k].getPos_x() + " " + Globals.ships_p1[k].getPos_y());
        }
        for (int k = 0; k < 5; k++) {
            System.out.println(Globals.ships_p2[k].getLength() + " " + Globals.ships_p2[k].getPos_x() + " " + Globals.ships_p2[k].getPos_y());
        }
    }


    public void customizeFrame() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));
        c.add(player_p, BorderLayout.NORTH);
        c.add(grid, BorderLayout.CENTER);

        player_p.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        player_p.add(p1_l);
        p1_l.setFont(new Font("Arial", Font.BOLD, 24));

        grid.setLayout(new GridLayout(10, 10));

        for (int i = 0; i < Globals.gridButtons.length; i++) {
            for (int j = 0; j < Globals.gridButtons[i].length; j++) {
                Globals.gridButtons[i][j] = new JButton();
                grid.add(Globals.gridButtons[i][j]);
            }
        }

        setBounds(100, 100, 500, 550);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler uscire? Tornerai alla Home.", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            new Home();
            dispose();
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }


    //metodi inutilizzati

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
