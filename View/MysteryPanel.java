package GUIProjekt.View;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MysteryPanel extends JPanel {
    private JLabel mysteryFrage, mysteryWort, mysteryZeit, mysteryPunkte;
    public MysteryPanel() {
        this.setLayout(new BorderLayout(10, 10));
    }
    public void mysteryGame() {
        this.removeAll();
        JPanel oben = new JPanel(new BorderLayout());
        oben.setBackground(new Color(236, 246, 249));
        mysteryZeit = new JLabel("00:00");
        mysteryPunkte = new JLabel("Punkte: 0");
        mysteryZeit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        mysteryPunkte.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        oben.add(mysteryZeit, BorderLayout.LINE_START);
        oben.add(mysteryPunkte, BorderLayout.LINE_END);
        this.add(oben, BorderLayout.PAGE_START);
        mysteryFrage = new JLabel("Untersuche den ersten Hinweis!");
        this.add(mysteryFrage, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void fallErklaerung(ActionListener listener){
    }
}
