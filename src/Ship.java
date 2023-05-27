public class Ship {
    int length; //lunghezza della nave
    int pos_x; //posizione orizzontale della nave
    int pos_y; //posizione verticale della nave

    //costruttore
    public Ship (int length, int pos_x, int pos_y) {
        this.length = length;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public int getLength() {
        return length;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }


}
