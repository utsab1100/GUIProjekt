package GUIProjekt.View;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
public class CyberMysteryPanel extends JPanel {
    private JButton mysterybtn, quizbtn, verwaltungsbtn, exitbtn;
    private JButton continuebtn, backtomenu;
    private JLabel header;
    private int sekunden=0;
    private Timer timer;
    private JPanel start;
    private JPanel quizPanel;
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
    public CyberMysteryPanel() {
        this.setLayout(new BorderLayout());
        // Hauptmenü
        start = new JPanel();
        this.frageAnzeige = new JPanel();
        this.anzeigeOben = new JPanel(new BorderLayout());
        frage = new JLabel();
        bildAnzeige = new JLabel();
        bildAnzeige.setAlignmentX(Component.CENTER_ALIGNMENT);
        bildAnzeige.setVisible(false);
        this.frageAnzeige.setVisible(false);
        this.frage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        start.setLayout(new BoxLayout(start, BoxLayout.PAGE_AXIS));
        start.setBackground(new Color(59, 77, 102));
        mysterybtn = new JButton("Mystery");
        quizbtn = new JButton("Quiz");
        verwaltungsbtn = new JButton("Verwaltung");
        exitbtn = new JButton("Beenden");
        continuebtn = new JButton("Antwort prüfen");
        backtomenu = new JButton("Zurück zum Menü");
        header = new JLabel("CYBER MYSTERY");
        images = new JLabel[]{
                new JLabel(new ImageIcon("assets/mitm.jpg")),
                new JLabel(new ImageIcon("assets/ddos.png")),
                new JLabel(new ImageIcon("assets/dns_poisining.png"))
        };
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        mysterybtn.setActionCommand("Mystery");
        quizbtn.setActionCommand("Quiz");
        verwaltungsbtn.setActionCommand("Verwaltung");
        exitbtn.setActionCommand("Beenden");
        continuebtn.setActionCommand("Antwort prüfen");
        mysterybtn.setFont(buttonFont);
        quizbtn.setFont(buttonFont);
        verwaltungsbtn.setFont(buttonFont);
        exitbtn.setFont(buttonFont);
        header.setFont(new Font(Font.MONOSPACED, Font.BOLD, 112));
        header.setForeground(new Color(238, 238, 238));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        mysterybtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        verwaltungsbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        int buttonwidth = 400;
        int buttonheight = 80;
        Color buttonColor = new Color(255, 255, 255);
        mysterybtn.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        quizbtn.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        verwaltungsbtn.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        exitbtn.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        mysterybtn.setMaximumSize(new Dimension(buttonwidth, buttonheight));
        quizbtn.setMaximumSize(new Dimension(buttonwidth, buttonheight));
        verwaltungsbtn.setMaximumSize(new Dimension(buttonwidth, buttonheight));
        exitbtn.setMaximumSize(new Dimension(buttonwidth, buttonheight));
        mysterybtn.setBackground(buttonColor);
        quizbtn.setBackground(buttonColor);
        verwaltungsbtn.setBackground(buttonColor);
        exitbtn.setBackground(buttonColor);
        start.add(Box.createVerticalGlue());
        start.add(header);
        start.add(Box.createVerticalStrut(100));
        start.add(mysterybtn);
        start.add(Box.createVerticalStrut(30));
        start.add(quizbtn);
        start.add(Box.createVerticalStrut(30));
        start.add(verwaltungsbtn);
        start.add(Box.createVerticalStrut(30));
        start.add(exitbtn);
        start.add(Box.createVerticalGlue());
        this.add(start, BorderLayout.CENTER);
    }
    public JButton getMysterybtn() {
        return mysterybtn;
    }
    public JButton getQuizbtn() {
        return quizbtn;
    }
    public JButton getVerwaltungsbtn() {
        return verwaltungsbtn;
    }
    public JButton getExitbtn() {
        return exitbtn;
    }
    public JButton getContinuebtn() {
        return continuebtn;
    }
    public JButton getBacktomenu() {
        return backtomenu;
    }
    public void quizGame(){
        // Erstellung der Frage
        frageZaehler = new JLabel("Frage 1/11");
        frageZaehler.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        frageZaehler.setForeground(Color.WHITE);
        frageZaehler.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Menü verstecken
        start.setVisible(false);
        quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBackground(new Color(57, 158, 207));
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
        quizPanel.add(anzeigeOben, BorderLayout.NORTH);
        quizPanel.add(unten, BorderLayout.SOUTH);
        quizPanel.add(frageAnzeige, BorderLayout.CENTER);
        this.add(quizPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void menue() {
        // Quiz entfernen
        if (quizPanel != null) {
            this.remove(quizPanel);
        }
        if (timer != null) {
            timer.stop();
        }
        if(eingabe != null){
            eingabe.setText("");
        }
        // Menü wieder anzeigen
        start.setVisible(true);
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
        if(index >= 0 && index < images.length){
            ImageIcon icon = (ImageIcon) images[index].getIcon();
            System.out.println("Bilddatei: " + icon.getDescription());
            System.out.println("Empfangener Index: " + index);
            int originalBreite = icon.getIconWidth();
            int originalHoehe = icon.getIconHeight();
            if(originalBreite > 0 && originalHoehe > 0){
                int maxBreite = 400;
                int maxHoehe = 250;
                double faktor = Math.min((double) maxBreite / originalBreite, (double) maxHoehe / originalHoehe);
                int neueBreite = Math.max(1, (int) (originalBreite * faktor));
                int neueHoehe = Math.max(1, (int) (originalHoehe * faktor));
                Image skaliert = icon.getImage().getScaledInstance(neueBreite, neueHoehe, Image.SCALE_SMOOTH);
                bildAnzeige.setIcon(new ImageIcon(skaliert));
                bildAnzeige.setPreferredSize(new Dimension(maxBreite, maxHoehe));
            } else {
                bildAnzeige.setIcon(null);
            }
        } else {
            System.out.println("FEHLER: Ungültiger Bildindex");
            bildAnzeige.setIcon(null);
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
    public void setFrageZaehler(int aktuelleFrage, int gesamtFragen) {
        frageZaehler.setText("Frage " + aktuelleFrage + "/" + gesamtFragen);
    }
}
