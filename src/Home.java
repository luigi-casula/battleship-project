import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Home extends JFrame implements ActionListener, WindowListener {
    JLabel title_l = new JLabel("Battleship"); //titolo
    JLabel credits_l = new JLabel("Luigi Casula, Lorenzo Caboni"); //crediti

    JButton newgame_b = new JButton("New Game"); //nuova partita
    JButton scoreboard_b = new JButton("Scoreboard"); //apre la scoreboard
    JButton recentgames_b = new JButton("Recent Games"); //apre la lista delle ultime partite
    JButton exit_b = new JButton("Exit"); //chiude la finestra home

    JPanel title_p = new JPanel(); //pannello titolo (include anche i crediti)
    JPanel buttons_p = new JPanel(); //pannello che contiene i bottoni (non include il bottone exit)
    JPanel exit_p = new JPanel(); //pannello che contiene il bottone exit

    public Home() { //costruttore
        super("Battleship - Home"); //titolo della finestra
        customizeFrame(); //metodo dove vengono definiti tutti i parametri visivi
    }

    public void customizeFrame() {
        Container c = getContentPane(); //container
        c.setLayout(new BorderLayout()); //definizione del layout manager

        c.add(title_p, BorderLayout.NORTH); //aggiunta del pannello titolo
        c.add(buttons_p, BorderLayout.CENTER); //aggiunta del pannello bottoni
        c.add(exit_p, BorderLayout.SOUTH); //aggiunta del pannello exit

        title_p.setLayout(new BorderLayout()); //definizione del layout manager del pannello titolo
        title_p.add(title_l, BorderLayout.NORTH); //aggiunta del pannello titolo al pannello titolo
        title_p.add(credits_l, BorderLayout.SOUTH); //aggiunta del pannello crediti al pannello titolo

        buttons_p.setLayout(new BorderLayout()); //definizione del layout manager del pannello bottoni
        buttons_p.add(newgame_b, BorderLayout.NORTH); //aggiunta del bottone nuova partita al pannello bottoni
        buttons_p.add(scoreboard_b, BorderLayout.CENTER); //aggiunta del bottone scoreboard al pannello bottoni
        buttons_p.add(recentgames_b, BorderLayout.SOUTH); //aggiunta del bottone partite recenti al pannello bottoni

        exit_p.setLayout(new FlowLayout()); //definizione del layout manager del pannello exit
        exit_p.add(exit_b); //aggiunta del bottone exit al pannello exit

        setBounds(100, 100, 300, 500);
        setResizable(false);
        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}