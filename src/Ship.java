public class Ship {
    int length; //lunghezza della nave
    int pos_x; //posizione orizzontale della nave
    int pos_y; //posizione verticale della nave
    boolean isVertical; //orientamento della nave


    //costruttore
    public Ship (int length, int pos_x, int pos_y, boolean isVertical) {
        this.length = length;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.isVertical = isVertical;
    }

    //metodo per il posizionamento della nave nella griglia di gioco
    public void ShipPositioning (int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }







}
