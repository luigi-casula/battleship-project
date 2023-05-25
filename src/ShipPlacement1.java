import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class ShipPlacement1 extends JFrame implements ActionListener, WindowListener, MouseListener {

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

    JLabel name;
    JLabel name2;

    JButton[][] gridButtons = new JButton[10][10];

    //dichiarazione dei pannelli
    JPanel ships = new JPanel(); //pannello delle navi
    JPanel grid = new JPanel(); //pannello contenente la griglia


    private Border original;
    private Border originalShip;

    //costruttore
    public ShipPlacement1(String p1, String p2) throws IOException {
        super("Battleship - Playing");
        name.setText(p1);
        name2.setText(p2);
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
        ships.setLayout(new GridLayout(6,1,10,10));
        ships.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        ships.add(name);
        ships.add(ship2x1_l);
        ships.add(ship1_3x1_l);
        ships.add(ship2_3x1_l);
        ships.add(ship4x1_l);
        ships.add(ship5x1_l);

        ship1_3x1_l.setEnabled(false);
        ship2_3x1_l.setEnabled(false);
        ship4x1_l.setEnabled(false);
        ship5x1_l.setEnabled(false);

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

        name.setFont(new Font("Arial", Font.BOLD, 30));
        name.setHorizontalAlignment(JLabel.CENTER);

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
            ship5x1_l.setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (ship2x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
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
                                for (int i = 0; i < gridButtons.length; i++) {
                                    for (int j = 0; j < gridButtons[i].length; j++) {
                                        if (e.getSource() == gridButtons[i][j] && ship2x1_l_state) {
                                            if (j == 9) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                ship2x1_l.setBorder(originalShip);
                                                ship2x1_l.setEnabled(false);
                                                ship1_3x1_l.setEnabled(true);
                                                ship2_3x1_l.setEnabled(false);
                                                ship4x1_l.setEnabled(false);
                                                ship5x1_l.setEnabled(false);
                                                ship2x1_l_state = false;
                                            }
                                            else if (j < 9) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                ship2x1_l.setBorder(originalShip);
                                                ship2x1_l.setEnabled(false);
                                                ship1_3x1_l.setEnabled(true);
                                                ship2_3x1_l.setEnabled(false);
                                                ship4x1_l.setEnabled(false);
                                                ship5x1_l.setEnabled(false);
                                                ship2x1_l_state = false;
                                            }
                                        }
                                    }
                                }
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (!isClicked) { // reimposta il colore del bordo solo se isClicked è false
                                    for (JButton[] gridButton : gridButtons) {
                                        for (int j = 0; j < gridButton.length; j++) {
                                            if (e.getSource() == gridButton[j] && gridButton[j].isEnabled()) {
                                                if (j == 9) {
                                                    gridButton[j].setBorder(original);
                                                    gridButton[j - 1].setBorder(original);
                                                } else if (j < 9) {
                                                    gridButton[j].setBorder(original);
                                                    gridButton[j + 1].setBorder(original);
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
                    if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                        if (j == 9) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                        }
                        else if (j == 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                        }
                        else if (j < 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                        }
                        gridButtons[i][j].addMouseListener(new MouseAdapter() {
                            boolean isClicked = false;
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                for (int i = 0; i < gridButtons.length; i++) {
                                    for (int j = 0; j < gridButtons[i].length; j++) {
                                        if (e.getSource() == gridButtons[i][j] && ship1_3x1_l_state) {
                                            if (j == 9) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                ship1_3x1_l.setBorder(originalShip);
                                                ship1_3x1_l.setEnabled(false);
                                                ship2_3x1_l.setEnabled(true);
                                                ship1_3x1_l_state = false;
                                            }
                                            else if (j == 8) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                ship1_3x1_l.setBorder(originalShip);
                                                ship1_3x1_l.setEnabled(false);
                                                ship2_3x1_l.setEnabled(true);
                                                ship1_3x1_l_state = false;
                                            }
                                            else if (j < 8) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(0,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                ship1_3x1_l.setBorder(originalShip);
                                                ship1_3x1_l.setEnabled(false);
                                                ship2_3x1_l.setEnabled(true);
                                                ship1_3x1_l_state = false;
                                            }
                                        }
                                    }
                                }
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (!isClicked) { // reimposta il colore del bordo solo se isClicked è false
                                    for (int i = 0; i < gridButtons.length; i++) {
                                        for (int j = 0; j < gridButtons[i].length; j++) {
                                            if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                                                if (j == 9) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                }
                                                else if (j == 8) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                }
                                                else if (j < 8) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);
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
        if (ship2_3x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                        if (j == 9) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                        }
                        else if (j == 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                        }
                        else if (j < 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                        }
                        gridButtons[i][j].addMouseListener(new MouseAdapter() {
                            boolean isClicked = false;
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                for (int i = 0; i < gridButtons.length; i++) {
                                    for (int j = 0; j < gridButtons[i].length; j++) {
                                        if (e.getSource() == gridButtons[i][j] && ship2_3x1_l_state) {
                                            if (j == 9) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                ship2_3x1_l.setBorder(originalShip);
                                                ship2_3x1_l.setEnabled(false);
                                                ship4x1_l.setEnabled(true);
                                                ship1_3x1_l_state = false;
                                            }
                                            else if (j == 8) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                ship2_3x1_l.setBorder(originalShip);
                                                ship2_3x1_l.setEnabled(false);
                                                ship4x1_l.setEnabled(true);
                                                ship2x1_l_state = false;
                                            }
                                            else if (j < 8) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(0,0,255)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                ship2_3x1_l.setBorder(originalShip);
                                                ship2_3x1_l.setEnabled(false);
                                                ship4x1_l.setEnabled(true);
                                                ship2_3x1_l_state = false;
                                            }
                                        }
                                    }
                                }
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (!isClicked) { // reimposta il colore del bordo solo se isClicked è false
                                    for (int i = 0; i < gridButtons.length; i++) {
                                        for (int j = 0; j < gridButtons[i].length; j++) {
                                            if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                                                if (j == 9) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                }
                                                else if (j == 8) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                }
                                                else if (j < 8) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);
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
        if (ship4x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                        if (j == 9) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j-3].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));

                        }
                        else if (j == 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                        }
                        else if (j == 7) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                        }
                        else if (j < 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                            gridButtons[i][j+3].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                        }
                        gridButtons[i][j].addMouseListener(new MouseAdapter() {
                            boolean isClicked = false;
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                for (int i = 0; i < gridButtons.length; i++) {
                                    for (int j = 0; j < gridButtons[i].length; j++) {
                                        if (e.getSource() == gridButtons[i][j] && ship4x1_l_state) {
                                            if (j == 9) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j-3].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                gridButtons[i][j-3].setEnabled(false);
                                                ship4x1_l.setBorder(originalShip);
                                                ship4x1_l.setEnabled(false);
                                                ship5x1_l.setEnabled(true);
                                                ship4x1_l_state = false;
                                            }
                                            else if (j == 8) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                ship4x1_l.setBorder(originalShip);
                                                ship4x1_l.setEnabled(false);
                                                ship5x1_l.setEnabled(true);
                                                ship4x1_l_state = false;
                                            }
                                            else if (j == 7) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                ship4x1_l.setBorder(originalShip);
                                                ship4x1_l.setEnabled(false);
                                                ship5x1_l.setEnabled(true);
                                                ship4x1_l_state = false;
                                            }
                                            else if (j < 7) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j+3].setBorder(BorderFactory.createLineBorder(new Color(255,255,0)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                gridButtons[i][j+3].setEnabled(false);
                                                ship4x1_l.setBorder(originalShip);
                                                ship4x1_l.setEnabled(false);
                                                ship5x1_l.setEnabled(true);
                                                ship4x1_l_state = false;
                                            }
                                        }
                                    }
                                }
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (!isClicked) { // reimposta il colore del bordo solo se isClicked è false
                                    for (int i = 0; i < gridButtons.length; i++) {
                                        for (int j = 0; j < gridButtons[i].length; j++) {
                                            if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                                                if (j == 9) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                    gridButtons[i][j-3].setBorder(original);
                                                }
                                                else if (j == 8) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);

                                                }
                                                else if (j == 7) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);

                                                }
                                                else if (j < 7) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);
                                                    gridButtons[i][j+3].setBorder(original);

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
        if (ship5x1_l_state) {
            for (int i = 0; i < gridButtons.length; i++) {
                for (int j = 0; j < gridButtons[i].length; j++) {
                    if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                        if (j == 9) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-4].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));

                        }
                        else if (j == 8) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                        }
                        else if (j == 7) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                        }
                        else if (j == 6) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                        }
                        else if (j < 6) {
                            gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                            gridButtons[i][j+4].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                        }
                        gridButtons[i][j].addMouseListener(new MouseAdapter() {
                            boolean isClicked = false;
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                for (int i = 0; i < gridButtons.length; i++) {
                                    for (int j = 0; j < gridButtons[i].length; j++) {
                                        if (e.getSource() == gridButtons[i][j] && ship5x1_l_state) {
                                            if (j == 9) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-4].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                gridButtons[i][j-3].setEnabled(false);
                                                gridButtons[i][j-4].setEnabled(false);
                                                ship5x1_l.setBorder(originalShip);
                                                ship5x1_l_state = false;
                                            }
                                            else if (j == 8) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                gridButtons[i][j-3].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                ship5x1_l.setBorder(originalShip);
                                                ship5x1_l_state = false;
                                            }
                                            else if (j == 7) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j-2].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                ship5x1_l.setBorder(originalShip);
                                                ship5x1_l_state = false;
                                            }
                                            else if (j == 6) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j-1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j-1].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                gridButtons[i][j+3].setEnabled(false);
                                                ship5x1_l.setBorder(originalShip);
                                                ship5x1_l_state = false;
                                            }
                                            else if (j < 6) {
                                                isClicked = true;
                                                gridButtons[i][j].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+1].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+2].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+3].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j+4].setBorder(BorderFactory.createLineBorder(new Color(75,0,130)));
                                                gridButtons[i][j].setEnabled(false);
                                                gridButtons[i][j+1].setEnabled(false);
                                                gridButtons[i][j+2].setEnabled(false);
                                                gridButtons[i][j+3].setEnabled(false);
                                                gridButtons[i][j+4].setEnabled(false);
                                                ship5x1_l.setBorder(originalShip);
                                                ship5x1_l_state = false;
                                            }
                                            try {
                                                new ShipPlacement2(name2.getText());
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                            dispose();
                                        }
                                    }
                                }
                            }
                            @Override
                            public void mouseExited(MouseEvent e) {
                                if (!isClicked) { // reimposta il colore del bordo solo se isClicked è false
                                    for (int i = 0; i < gridButtons.length; i++) {
                                        for (int j = 0; j < gridButtons[i].length; j++) {
                                            if (e.getSource() == gridButtons[i][j] && gridButtons[i][j].isEnabled()) {
                                                if (j == 9) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                    gridButtons[i][j-3].setBorder(original);
                                                    gridButtons[i][j-4].setBorder(original);

                                                }
                                                else if (j == 8) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                    gridButtons[i][j-3].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                }
                                                else if (j == 7) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j-2].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);
                                                }
                                                else if (j == 6) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j-1].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);
                                                    gridButtons[i][j+3].setBorder(original);
                                                }
                                                else if (j < 6) {
                                                    gridButtons[i][j].setBorder(original);
                                                    gridButtons[i][j+1].setBorder(original);
                                                    gridButtons[i][j+2].setBorder(original);
                                                    gridButtons[i][j+3].setBorder(original);
                                                    gridButtons[i][j+4].setBorder(original);
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
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}