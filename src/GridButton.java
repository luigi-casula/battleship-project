import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridButton extends JButton implements ActionListener {
    Ship ship;

    public GridButton() {
        super();
        addActionListener(this);
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        for (Ship ship : Globals.ships_p1) {        //inizializzo delle variabili che prendono il valore delle coordinate della nave
            int startX = ship.getPos_x();   //coordinata x di inizio nave
            int startY = ship.getPos_y();   //coordinata y di inizio nave
            int endX = ship.getPos_x() + ship.getLength() - 1;  //coordinata x di fine nave

            int clickedX = -19;      //valori di default
            int clickedY = -1;
            for (int i = 0; i < Globals.gridButtons.length; i++) {      //ciclo per trovare il bottone cliccato
                for (int j = 0; j < Globals.gridButtons[i].length; j++) {
                    if (Globals.gridButtons[i][j] == clickedButton) {
                        clickedX = i;
                        clickedY = j;
                        break;
                    }
                }
            }

            if (clickedX >= startX && clickedX <= endX && clickedY == startY) {     //controllo corrispondenza bottone con nave
                System.out.println("Il bottone corrisponde a una nave del giocatore 1");
                break;
            }
        }
    }
}
