import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class GridButton extends JButton implements MouseListener { //creazione della griglia
    boolean clicked = false; //verifica se il bottone è premuto
    boolean enabled = true; //verifica se il bottone è abilitato

    GridButton[][] selectedGrid; //griglia utilizzata per il posizionamento

    Ship linkedShip; //nave cliccata
    Ship placingShip; //nave da posizionare


    int x, y;

    Border defaultBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

    //costruttore
    public GridButton() {
        super();
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBorder(defaultBorder);
    }

    //metodi getter e setter
    public Ship getLinkedShip() { //attualmente inutilizzato
        return linkedShip;
    }

    public void setLinkedShip(Ship ship) {
        this.linkedShip = ship;
    }

    //metodo utilizzato durante il posizionamento
    public void placingMode(Ship ship, GridButton[][] selectedGrid) {
        this.selectedGrid = selectedGrid;
        this.addMouseListener(this);
        placingShip = ship;

        for (int i = 0; i < selectedGrid.length; i++) {
            for (int j = 0; j < selectedGrid.length; j++) {
                selectedGrid[i][j].enabled = true;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { //metodo che gestisce il mouse passa sopra un bottone
        if (!clicked && enabled) { //enabled disabilita tutte le altre caselle
            if (x < 11 - placingShip.getLength()) {

                placingShip.usedButtons.addAll(Arrays.asList(selectedGrid[y]).subList(x, x + placingShip.getLength()));

                paintBorders();
            } else {
                int startX = x;
                int endX = startX - (placingShip.getLength() - 1);

                for (int i = startX; i >= endX; i--) {
                    placingShip.usedButtons.add(selectedGrid[y][i]);
                }

                paintBorders();

            }
        }
    }

    private void paintBorders() { //colora i bordi delle caselle
        for (GridButton g : placingShip.usedButtons) {
            if (g.enabled && !g.clicked) {
                g.setBorder(BorderFactory.createLineBorder(placingShip.shipColor, 3));
                placingShip.placeable = true;
            } else {
                g.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                placingShip.placeable = false;
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) { //riporta allo stato di default i bottoni non premuti
        for (GridButton g : placingShip.usedButtons) {
            g.setBorder(defaultBorder);
        }
        placingShip.usedButtons.clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) { //colora lo sfondo dei bottoni in base alla nave selezionata
        if (placingShip.placeable) {
            for (GridButton g : placingShip.usedButtons) {
                g.setBackground(placingShip.shipColor);
                g.setLinkedShip(placingShip);
                g.setEnabled(false);
                g.clicked = true;


                for (int i = g.y - 1; i <= g.y + 1; i++) { //selezionare e disabilitare le caselle adiacenti e la nave
                    for (int j = g.x - 1; j <= g.x + 1; j++) {
                        if (i >= 0 && i < selectedGrid.length && j >= 0 && j < selectedGrid[i].length) {
                            GridButton currentCell = selectedGrid[i][j];

                            // Escludo le celle delle navi
                            if (!placingShip.usedButtons.contains(currentCell)) {
                                currentCell.setEnabled(false);
                                currentCell.clicked = true;
                                currentCell.setBackground(new Color(187, 246, 252));
                            }
                        }
                    }
                }


                placingShip.setEnabled(false);
                placingShip.setVisible(false);

                for (int i = 0; i < selectedGrid.length; i++) {
                    for (int j = 0; j < selectedGrid.length; j++) {
                        selectedGrid[i][j].enabled = false;
                    }
                }
            }
        }
    }


    //    inutilizzati
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
