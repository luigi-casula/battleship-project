import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PlayingFrame extends JFrame implements ActionListener, WindowListener {
    JButton [][] grid = new JButton[10][10]; // matrice di labels

    JButton ship1x1 = new JButton("1 x 1");
    JButton ship2x1 = new JButton("2 x 1");
    JButton ship3x1 = new JButton("3 x 1");
    JButton ship4x1 = new JButton("4 x 1");



    JPanel ships = new JPanel(); // pannello che contiene le immagini delle navi
    JPanel gridPanel = new JPanel(); // pannello che contiene la griglia

    public PlayingFrame() {
        super("Playing");
        customizeFrame();
    }

    public void customizeFrame() {
        Container c = getContentPane();
        c.setLayout(new GridLayout(1, 2));
        c.add(ships);
        c.add(gridPanel);

        ships.setLayout(new GridLayout(2, 2,10,10));
        ships.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ships.add(ship1x1);
        ship1x1.setTransferHandler(new TransferHandler("icon"));
        ship1x1.addActionListener(this);
        ships.add(ship2x1);
        ship2x1.setTransferHandler(new TransferHandler("icon"));
        ship2x1.addActionListener(this);
        ships.add(ship3x1);
        ship3x1.setTransferHandler(new TransferHandler("icon"));
        ship3x1.addActionListener(this);
        ships.add(ship4x1);
        ship4x1.setTransferHandler(new TransferHandler("icon"));
        ship4x1.addActionListener(this);

        gridPanel.setLayout(new GridLayout(10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < 10; i++) { // doppio for per creare la griglia
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new JButton();
                grid[i][j].addActionListener(this);
                gridPanel.add(grid[i][j]);
            }
        }

        setVisible(true);
        setResizable(false);
        setBounds(100, 100, 800, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ship1x1 == e.getSource()) {
            ship1x1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            //grid1x1();
        }
        else if (ship2x1 == e.getSource()) {
            ship2x1.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            //grid2x1();
        }
        else if (ship3x1 == e.getSource()) {
            ship3x1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            //grid3x1();
        }
        else if (ship4x1 == e.getSource()) {
            ship4x1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            //grid4x1();
        }
    }

    /*public void grid1x1() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        button.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    }
                });
                //ship1x1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                //i = 10;
            }
        }
    }

    public void grid2x1() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        if (finalI == 9) {
                            button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                            grid[finalI -1][finalJ].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                        }
                        else {
                            button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                            grid[finalI +1][finalJ].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                        }
                    }
                });
                //i = 10;
                //ship2x1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }
    }

    public void grid3x1() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        if (finalI == 9) {
                            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI -1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI -2][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        } else if (finalI == 8) {
                            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI +1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI -1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        } else if (finalI == 0) {
                            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI +1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI +2][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        } else if (finalI == 1) {
                            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI +1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI -1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        } else {
                            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI +1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                            grid[finalI -1][finalJ].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                        }
                    }
                });
                //i = 10;
                //ship3x1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            }
        }
    }

    public void grid4x1() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    }
                });
            }
        }
    }*/

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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
