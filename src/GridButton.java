import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class GridButton extends JButton implements MouseListener {
    boolean clicked = false;
    boolean enabled = true;

    GridButton[][] selectedGrid;

    Ship linkedShip;
    Ship placingShip;


    int x, y;

    Border defaultBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

    public GridButton() {
        super();
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBorder(defaultBorder);
    }

    public Ship getLinkedShip() {
        return linkedShip;
    }

    //    linked ship
    public void setLinkedShip(Ship ship) {
        this.linkedShip = ship;
    }

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
    public void mouseEntered(MouseEvent e) {
        if (!clicked && enabled) {
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

    private void paintBorders() {
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
    public void mouseExited(MouseEvent e) {
        for (GridButton g : placingShip.usedButtons) {
            g.setBorder(defaultBorder);
        }
        placingShip.usedButtons.clear();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (placingShip.placeable) {
            for (GridButton g : placingShip.usedButtons) {
                g.setBackground(placingShip.shipColor);
                g.setLinkedShip(placingShip);

                g.setEnabled(false);
                g.clicked = true;


                for (int i = g.y - 1; i <= g.y + 1; i++) {
                    for (int j = g.x - 1; j <= g.x + 1; j++) {
                        if (i >= 0 && i < selectedGrid.length && j >= 0 && j < selectedGrid[i].length) {
                            GridButton currentCell = selectedGrid[i][j];

                            // Exclude the ship cells from being grayed out
                            if (!placingShip.usedButtons.contains(currentCell)) {
                                currentCell.setEnabled(false);
                                currentCell.clicked = true;
                                currentCell.setBackground(Color.GRAY);
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


    //    useless
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
