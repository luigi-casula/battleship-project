import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Ship extends JButton {
    Vector<GridButton> usedButtons = new Vector<>(); //vettore che contiene i bottoni utilizzati dalla nave
    Color shipColor; //colore della nave in base alla sua lunghezza

    boolean state; //inutilizzato
    boolean placeable; //controlla se la nave è posizionabile

    int length; //lunghezza della nave

    //costruttore
    public Ship() {

    }

    public void setLength() {
        this.length = Math.round((float) this.getIcon().getIconWidth() / 75);
        System.out.printf("Length: " + length);
    }

    public int getLength() {
        return length;
    }

    public Color getShipColor() {
        return this.shipColor;
    }

    public void setShipColor(Color shipColor) {
        this.shipColor = shipColor;
        this.setBorder(BorderFactory.createLineBorder(this.shipColor));
    }

}
