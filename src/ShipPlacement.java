import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class ShipPlacement extends JFrame implements ActionListener, WindowListener {
    Ship[] ships;

    JLabel name = new JLabel();
    JLabel name2 = new JLabel();

    GridButton[][] gridButtons;

    //dichiarazione dei pannelli
    JPanel shipSelectorPanel = new JPanel(); //pannello delle navi
    JPanel gridPlacementPanel = new JPanel(); //pannello contenente la griglia

    //costruttore
    public ShipPlacement(int player) throws IOException {
        super("Battleship - Playing");

        if (player == 1) {
            ships = Globals.ships_p1;
            gridButtons = Globals.gridButtons_p1;
        } else {
            ships = Globals.ships_p2;
            gridButtons = Globals.gridButtons_p2;
        }

        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship();
            ships[i].setIcon(new ImageIcon(String.format("src/images/ship_%d.png", i + 1))); //formattazione dell'immagine
            ships[i].setLength();
        }
//colori di ogni nave
        ships[0].setShipColor(new Color(255, 0, 0));
        ships[1].setShipColor(new Color(0, 255, 0));
        ships[2].setShipColor(new Color(0, 0, 255));
        ships[3].setShipColor(new Color(255, 255, 0));
        ships[4].setShipColor(new Color(75, 0, 130));


        name.setText("Player 1");
        name2.setText("Player 2");


        customizeFrame();
    }

    //metodo per la personalizzazione della finestra
    public void customizeFrame() {

        //gestione del container
        Container c = getContentPane();
        c.setLayout(new GridLayout(1, 2, 10, 10));
        c.add(shipSelectorPanel);
        c.add(gridPlacementPanel);

        //gestione pannello navi
        shipSelectorPanel.setLayout(new GridLayout(6, 1, 10, 10));
        shipSelectorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        shipSelectorPanel.add(name);

        for (Ship ship : ships) {
            shipSelectorPanel.add(ship);
            ship.setBorder(null);
            ship.setBackground(new Color(238, 238, 238));
            ship.addActionListener(this);
        }


        name.setFont(new Font("Arial", Font.BOLD, 30));
        name.setHorizontalAlignment(JLabel.CENTER);

        //gestione pannello griglia
        gridPlacementPanel.setLayout(new GridLayout(10, 10));
        gridPlacementPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));

        //aggiunta dei bottoni alla griglia

        for (int i = 0; i < gridButtons.length; i++) {
            for (int j = 0; j < gridButtons.length; j++) {
                gridButtons[i][j] = new GridButton();

                gridButtons[i][j].y = i;
                gridButtons[i][j].x = j;

                gridPlacementPanel.add(gridButtons[i][j]);
            }
        }

        // altri parametri della finestra
        addWindowListener(this); // aggiunta del listener alla finestra
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setBounds(100, 100, 1000, 500);
    }


    //    click on ship
    @Override
    public void actionPerformed(ActionEvent e) {
        Ship selectedShip = (Ship) e.getSource();

        for (int i = 0; i < gridButtons.length; i++) {
            for (int j = 0; j < gridButtons.length; j++) {
                gridButtons[i][j].placingMode(selectedShip, gridButtons);
            }
        }
    }

    @Override
    public void windowClosing(WindowEvent e) { //finestra di dialogo per la conferma della chiusura
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