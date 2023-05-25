import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewGame extends JFrame implements ActionListener {

    JLabel p1_l = new JLabel("Player 1");
    JLabel p2_l = new JLabel("Player 2");
    JTextField p1_t = new JTextField();
    JTextField p2_t = new JTextField();
    JButton start = new JButton("Start");

    JPanel names_p = new JPanel();
    JPanel start_p = new JPanel();

    Font defaultFont = new Font("Arial", Font.PLAIN, 16); //dichiarazione Font generale
    Font bold = defaultFont.deriveFont(Font.BOLD, 18); //dichiarazione Font grassetto
    public NewGame() {
        super("New Game");
        customizeFrame();
    }

    public void customizeFrame() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout(10,10));

        c.add(names_p, BorderLayout.CENTER);
        c.add(start_p, BorderLayout.SOUTH);

        names_p.setLayout(new GridLayout(2,2,10,10));
        names_p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        names_p.add(p1_l);
        p1_l.setFont(bold);
        names_p.add(p2_l);
        p2_l.setFont(bold);
        names_p.add(p1_t);
        p1_t.setFont(defaultFont);
        names_p.add(p2_t);
        p2_t.setFont(defaultFont);

        start_p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        start.setFont(bold);
        start_p.add(start);
        start.addActionListener(this);

        setBounds(100,100,500, 200);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new ShipPlacement1(p1_t.getText(), p2_t.getText());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        dispose();
    }
}
