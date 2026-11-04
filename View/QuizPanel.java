package GUIProjekt.View;
import java.awt.*;
import javax.swing.*;
public class QuizPanel extends JPanel{
    private JButton continuebtn, backtomenu;
    private int sekunden=0;
    private Timer timer;
    private JLabel frage;
    private JPanel frageAnzeige;
    private JTextField eingabe;
    private JLabel punkte;
    private JLabel time;
    private JPanel anzeigeOben;
    private JPanel unten;
    private JLabel bildAnzeige;
    private JLabel[] images;
    private JLabel frageZaehler;
    public QuizPanel(){
        this.setLayout(new BorderLayout());
        // Quiz-Komponenten erstellen
        this.frageAnzeige = new JPanel();
        this.anzeigeOben = new JPanel(new BorderLayout());
        frage = new JLabel();
        bildAnzeige = new JLabel();
        bildAnzeige.setAlignmentX(Component.CENTER_ALIGNMENT);
        bildAnzeige.setVisible(false);
        frageAnzeige.setVisible(false);
        frage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        continuebtn = new JButton("Antwort prüfen");
        backtomenu = new JButton("Zurück zum Menü");
        images = new JLabel[]{
                new JLabel(new ImageIcon("assets/mitm.jpg")),
                new JLabel(new ImageIcon("assets/ddos.png")),
                new JLabel(new ImageIcon("assets/dns_poisining.png"))
        };
        continuebtn.setActionCommand("Antwort prüfen");
    }
    public JButton getContinuebtn() {
        return continuebtn;
    }
    public JButton getBacktomenu() {
        return backtomenu;
    }
    public int getSekunden() {return sekunden;}
    public void quizGame(){
        // Erstellung der Frage
        frageZaehler = new JLabel("Frage 1/11");
        frageZaehler.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        frageZaehler.setForeground(Color.WHITE);
        frageZaehler.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(57, 158, 207));
        // Obere Anzeige
        anzeigeOben.setBackground(new Color(236, 246, 249));
        JPanel punkteAnzeige = new JPanel(new FlowLayout(FlowLayout.LEFT));
        punkte = new JLabel("Punkte: 0");
        punkte.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        punkte.setForeground(Color.BLACK);
        punkteAnzeige.add(punkte);
        JPanel zeitAnzeige = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        time = new JLabel();
        time.setText("Zeit: 00:00");
        time.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        time.setForeground(Color.BLACK);
        sekunden = 0;
        if(timer != null){
            timer.stop();
        }
        timer = new Timer(1000, e -> {
            sekunden++;
            int minuten = sekunden / 60;
            int restSekunden = sekunden % 60;
            time.setText(String.format("Zeit: %02d:%02d", minuten, restSekunden));
        });
        zeitAnzeige.add(time);
        anzeigeOben.add(punkte, BorderLayout.WEST);
        anzeigeOben.add(zeitAnzeige, BorderLayout.EAST);
        // Uhrzeit jede Sekunde aktualisieren
        timer.start();
        this.frageAnzeige.setVisible(true);
        frageAnzeige.setLayout(new BoxLayout(frageAnzeige, BoxLayout.Y_AXIS));
        frageAnzeige.setBackground(new Color(59, 77, 102));
        frage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        frage.setForeground(Color.WHITE);
        frage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.eingabe = new JTextField();
        frageAnzeige.removeAll();
        eingabe.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        eingabe.setMaximumSize(new Dimension(700, 60));
        eingabe.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuebtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        continuebtn.setPreferredSize(new Dimension(250, 60));
        continuebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        frageAnzeige.add(Box.createVerticalGlue());
        frageAnzeige.add(frageZaehler);
        frageAnzeige.add(Box.createVerticalStrut(15));
        frageAnzeige.add(frage);
        frageAnzeige.add(Box.createVerticalStrut(20));
        frageAnzeige.add(bildAnzeige);
        frageAnzeige.add(Box.createVerticalStrut(20));
        frageAnzeige.add(eingabe);
        frageAnzeige.add(Box.createVerticalStrut(30));
        frageAnzeige.add(continuebtn);
        frageAnzeige.add(Box.createVerticalGlue());
        // Zurück-Button
        backtomenu.setActionCommand("Zurück zum Menü");
        backtomenu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        unten = new JPanel(new FlowLayout(FlowLayout.CENTER));
        unten.setBackground(new Color(236, 246, 249));
        unten.add(backtomenu);
        this.add(anzeigeOben, BorderLayout.NORTH);
        this.add(unten, BorderLayout.SOUTH);
        this.add(frageAnzeige, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void setAktuelleFrage(String aktuelleFrage){
        this.frage.setText(aktuelleFrage);
        this.eingabe.setText("");
    }
    public void setPunkte(int punkte){
        this.punkte.setText("Punkte: " + punkte);
    }
    public void farbeAenderung(boolean richtig){
        if(richtig){
            anzeigeOben.setBackground(Color.GREEN);
            unten.setBackground(Color.GREEN);
        } else {
            anzeigeOben.setBackground(Color.RED);
            unten.setBackground(Color.RED);
        }
    }
    public String getEingabe(){
        return eingabe.getText();
    }
    public void zeigeTextfrage(String text){
        frage.setText(text);
        bildAnzeige.setVisible(false);
        eingabe.setVisible(true);
        continuebtn.setVisible(true);
        eingabe.setText("");
        frageAnzeige.revalidate();
        frageAnzeige.repaint();
    }
    public void zeigeBildfrage(String text, int index){
        frage.setText(text);
        if(index >= 0 && index < images.length) {
            ImageIcon icon = (ImageIcon) images[index].getIcon();
            Image bild = icon.getImage();
            Image skaliert = bild.getScaledInstance(400,250,Image.SCALE_SMOOTH);
            bildAnzeige.setIcon(new ImageIcon(skaliert));
        }
        bildAnzeige.setVisible(true);
        eingabe.setVisible(true);
        continuebtn.setVisible(true);
        eingabe.setText("");
        frageAnzeige.revalidate();
        frageAnzeige.repaint();
    }
    public void farbeAenderungNeutral(){
        anzeigeOben.setBackground(new Color(236, 246, 249));
        unten.setBackground(new Color(236, 246, 249));
    }
    public void quizFertig(){
        frage.setText("Quiz abgeschlossen! Deine Punkte: " + punkte.getText());
        bildAnzeige.setVisible(false);
        eingabe.setVisible(false);
        continuebtn.setVisible(false);
    }
    public void statistikAnzeigen(int richtige, int falsche, int sekunden) {
        int gesamt = richtige + falsche;
        int prozent = 0;
        if (gesamt > 0) {
            prozent = richtige * 100 / gesamt;
        }
        String zeitText = String.format("%02d:%02d", sekunden / 60, sekunden % 60);
        String text = "Quiz abgeschlossen!\n\n" + "Richtige Antworten: " + richtige + "\n" + "Falsche Antworten: " + falsche + "\n" + "Ergebnis: " + prozent + "%\n" + "Benötigte Zeit: " + zeitText;
        JOptionPane.showMessageDialog(this, text, "Quiz-Statistik", JOptionPane.INFORMATION_MESSAGE);
    }
    public void setFrageZaehler(int aktuelleFrage, int gesamtFragen) {
        frageZaehler.setText("Frage " + aktuelleFrage + "/" + gesamtFragen);
    }
    public void timerStoppen() {
        timer.stop();
    }
}
