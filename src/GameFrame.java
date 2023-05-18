import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private Ship[] ships = new Ship[5];
    public GameFrame() {

    }

    public void shipsManager() { //metodo per creare e gestire le navi
        ships[0] = new Ship(2, 0, 0, true);
        ships[1] = new Ship(3, 0, 0, true);
        ships[2] = new Ship(3, 0, 0, true);
        ships[3] = new Ship(4, 0, 0, true);
        ships[4] = new Ship(5, 0, 0, true);
    }



    public void customizeFrame() {

    }



}
