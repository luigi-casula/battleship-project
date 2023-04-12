import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try { //impostazione del look and feel di sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        new Home(); //apre una nuova finestra home
    }
}