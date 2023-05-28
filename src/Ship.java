import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Ship extends JButton {
    Vector<GridButton> usedButtons = new Vector<>();
    Color shipColor;

    boolean state;
    boolean placeable;

    int length;
    int x;
    int y;

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

    public int getPos_x() {
        return x;
    }

    public int getPos_y() {
        return y;
    }

    public Color getShipColor() {
        return this.shipColor;
    }

    public void setShipColor(Color shipColor) {
        this.shipColor = shipColor;
        this.setBorder(BorderFactory.createLineBorder(this.shipColor));
    }

}
