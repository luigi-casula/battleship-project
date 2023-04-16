import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PlayingFrame extends JFrame implements ActionListener, WindowListener {
    JLabel [][] grid = new JLabel[10][10]; // matrice di labels

    JPanel gridPanel = new JPanel(); // pannello che contiene la griglia

    public PlayingFrame() {
        super("Playing");
        customizeFrame();
    }

    public void customizeFrame() {
        Container c = getContentPane();
        c.add(gridPanel);

        gridPanel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; i++) { // doppio for per creare la griglia
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new JLabel();
                gridPanel.add(grid[i][j]);
                grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }

        setVisible(true);
        setResizable(false);
        setBounds(100, 100, 400, 400);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
