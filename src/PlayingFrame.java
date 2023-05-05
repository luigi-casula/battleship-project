import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class PlayingFrame extends JFrame implements ActionListener, WindowListener, MouseListener {

    //dichiarazione delle immagini delle navi
    BufferedImage ship2x1 = ImageIO.read(new File("src/images/ship_2x1.png")); //cacciatorpediniere (3 di questa categoria)
    BufferedImage ship1_3x1 = ImageIO.read(new File("src/images/ship1_3x1.png")); //sottomarini (3 di questa categoria)
    BufferedImage ship2_3x1 = ImageIO.read(new File("src/images/ship2_3x1.png")); //incrociatore (2 di questa categoria)
    BufferedImage ship4x1 = ImageIO.read(new File("src/images/ship_4x1.png")); //corazzata (1 di questa categoria)
    BufferedImage ship5x1 = ImageIO.read(new File("src/images/ship_5x1.png")); //portaerei (1 di questa categoria)

    //ridimensionamento delle immagini delle navi
    int ship2x1_w = 167;
    int ship2x1_h = 68;
    int ship1_3x1_w = 235;
    int ship1_3x1_h = 50;
    int ship2_3x1_w = 234;
    int ship2_3x1_h = 84;
    int ship4x1_w = 287;
    int ship4x1_h = 90;
    int ship5x1_w = 339;
    int ship5x1_h = 86;

    //scaling delle immagini
    Image ship2x1_sc = ship2x1.getScaledInstance(ship2x1_w, ship2x1_h, Image.SCALE_SMOOTH);
    Image ship1_3x1_sc = ship1_3x1.getScaledInstance(ship1_3x1_w, ship1_3x1_h, Image.SCALE_SMOOTH);
    Image ship2_3x1_sc = ship2_3x1.getScaledInstance(ship2_3x1_w, ship2_3x1_h, Image.SCALE_SMOOTH);
    Image ship4x1_sc = ship4x1.getScaledInstance(ship4x1_w, ship4x1_h, Image.SCALE_SMOOTH);
    Image ship5x1_sc = ship5x1.getScaledInstance(ship5x1_w, ship5x1_h, Image.SCALE_SMOOTH);

    //dichiarazione bottoni delle navi
    JButton ship2x1_l = new JButton(new ImageIcon(ship2x1_sc));
    JButton ship1_3x1_l = new JButton(new ImageIcon(ship1_3x1_sc));
    JButton ship2_3x1_l = new JButton(new ImageIcon(ship2_3x1_sc));
    JButton ship4x1_l = new JButton(new ImageIcon(ship4x1_sc));
    JButton ship5x1_l = new JButton(new ImageIcon(ship5x1_sc));

    //dichiarazione stati dei bottoni delle navi (menu di sinistra)
    boolean ship2x1_l_state = false;
    boolean ship1_3x1_l_state = false;
    boolean ship2_3x1_l_state = false;
    boolean ship4x1_l_state = false;
    boolean ship5x1_l_state = false;


    JButton[][] gridButtons = new JButton[10][10];

    //dichiarazione dei pannelli
    JPanel ships = new JPanel(); //pannello delle navi
    JPanel grid = new JPanel(); //pannello contenente la griglia


    private Border original;
    private Border originalShip;

    //costruttore
    public PlayingFrame() throws IOException {
        super("Battleship - Playing");
        customizeFrame();
    }

    //metodo per la personalizzazione della finestra
    public void customizeFrame() {

        //gestione del container
        Container c = getContentPane();
        c.setLayout(new GridLayout(1,2,10,10));
        c.add(ships);
        c.add(grid);

        //gestione pannello navi
        ships.setLayout(new GridLayout(5,1,10,10));
        ships.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        ships.add(ship2x1_l);
        ships.add(ship1_3x1_l);
        ships.add(ship2_3x1_l);
        ships.add(ship4x1_l);
        ships.add(ship5x1_l);

        ship2x1_l.setBorder(null);
        ship2x1_l.setBackground(new Color(238, 238, 238));
        ship1_3x1_l.setBorder(null);
        ship1_3x1_l.setBackground(new Color(238, 238, 238));
        ship2_3x1_l.setBorder(null);
        ship2_3x1_l.setBackground(new Color(238, 238, 238));
        ship4x1_l.setBorder(null);
        ship4x1_l.setBackground(new Color(238, 238, 238));
        ship5x1_l.setBorder(null);
        ship5x1_l.setBackground(new Color(238, 238, 238));

        ship2x1_l.addActionListener(this);
        ship1_3x1_l.addActionListener(this);
        ship2_3x1_l.addActionListener(this);
        ship4x1_l.addActionListener(this);
        ship5x1_l.addActionListener(this);

        //gestione pannello griglia
        grid.setLayout(new GridLayout(10,10));
        grid.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));

        //aggiunta dei bottoni alla griglia
        for (int i = 0; i < gridButtons.length; i++) {
            for (int j = 0; j < gridButtons[i].length; j++) {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].addMouseListener(this);
                grid.add(gridButtons[i][j]);
            }
        }
        original = gridButtons[0][0].getBorder();
        originalShip = ship2x1_l.getBorder();

        //altri parametri della finestra
        setVisible(true);
        setBounds(100,100, 1000, 500);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ship2x1_l) {
            ship2x1_l_state = true;
            ship2x1_l.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
            ship1_3x1_l.setEnabled(false);
            ship2_3x1_l.setEnabled(false);
            ship4x1_l.setEnabled(false);
            ship5x1_l.setEnabled(false);
        }
        if (e.getSource() == ship1_3x1_l) {
            ship1_3x1_l_state = true;
            ship1_3x1_l.setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
        }
        if (e.getSource() == ship2_3x1_l) {
            ship2_3x1_l_state = true;
            ship2_3x1_l.setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
        }
        if (e.getSource() == ship4x1_l) {
            ship4x1_l_state = true;
            ship4x1_l.setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
        }
        if (e.getSource() == ship5x1_l) {
            ship5x1_l_state = true;
            ship5x1_l.setBorder(BorderFactory.createLineBorder(new Color(0,255,255)));
        }
//        if (ship2x1_l_state) {
//            int x = 0;
//            uscita:
//            for (int i = 0; i < gridButtons.length; i++) {
//                for (int j = 0; j < gridButtons[i].length; j++) {
//                    if (e.getSource() == gridButtons[i][j]) {
//                        x++;
//                        if (x < 4)
//                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
//                        else
//                            break uscita;
//                    }
//                }
//            }
//            ship2x1_l.setBorder(originalShip);
//            ship2x1_l_state = false;
//            ship2x1_l.setEnabled(true);
//            ship1_3x1_l.setEnabled(true);
//            ship2_3x1_l.setEnabled(true);
//            ship4x1_l.setEnabled(true);
//            ship5x1_l.setEnabled(true);
//        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (ship2x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        if (j == 9) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                        }
                        else if (j < 9) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                        }
                        gridButtons[i][j].addMouseListener(new MouseAdapter() {
                            boolean isClicked = false;
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                int x = 0;
                                for (int i = 0; i < gridButtons.length; i++) {
                                    for (int j = 0; j < gridButtons[i].length; j++) {
                                        if (e.getSource() == gridButtons[i][j]) {
                                            if (x < 4) {
                                                if (j == 9) {
                                                    gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                    gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                    x++;
                                                }
                                                else if (j < 9) {
                                                    gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                    gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                    x++;
                                                }
                                            }
                                            else {
                                                ship2x1_l.setBorder(originalShip);
                                                ship2x1_l.setEnabled(true);
                                                ship1_3x1_l.setEnabled(true);
                                                ship2_3x1_l.setEnabled(true);
                                                ship4x1_l.setEnabled(true);
                                                ship5x1_l.setEnabled(true);
                                                ship2x1_l_state = false;
                                            }
                                            isClicked = true; // imposta isClicked su true quando il mouse viene cliccato
                                        }
                                    }
                                }
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (!isClicked) { // reimposta il colore del bordo solo se isClicked è false
                                    for (int i = 0; i < gridButtons.length; i++) {
                                        for (int j = 0; j < gridButtons[i].length; j++) {
                                            if (e.getSource() == gridButtons[i][j]) {
                                                if (j == 9) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                }
                                                else if (j < 9) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
        if (ship1_3x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                    }
                }
            }
        }
        if (ship2_3x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                    }
                }
            }
        }
        if (ship4x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                    }
                }
            }
        }
        if (ship5x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,255)));
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (ship2x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        if (j == 9) {
                            gridButtons[i][j].setBorder(original);
                            gridButtons[i][j-1].setBorder(original);
                        }
                        else if (j < 9) {
                            gridButtons[i][j].setBorder(original);
                            gridButtons[i][j+1].setBorder(original);
                        }
                    }
                }
            }
        }
        if (ship1_3x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                    }
                }
            }
        }
        if (ship2_3x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                    }
                }
            }
        }
        if (ship4x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                    }
                }
            }
        }
        if (ship5x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j]) {
                        gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,255)));
                    }
                }
            }
        }
    }

    //metodi inutilizzati

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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}