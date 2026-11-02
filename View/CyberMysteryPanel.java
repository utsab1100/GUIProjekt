package GUIProjekt.View;
import java.awt.*;
import java.awt.event.ActionListener;
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
    private JPanel fall;
    private JButton startBtn;
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
    private JPanel mysteryPanel;
    private JLabel mysteryFrage, mysteryWort, mysteryZeit, mysteryPunkte;
    private JPanel verwaltungPanel;
    private JTextArea frageEingabe;
    private JTextField antwortEingabe;
    private JPanel fragenListe;
    private JButton speicheBtn;
    private int bearbeiteteFrage = -1;
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
    public JButton getSpeicheBtn(){return speicheBtn;}
    public JTextArea getFrageEingabe() {return frageEingabe;}
    public JTextField getAntwortEingabe() {return antwortEingabe;}
    public int getBearbeiteteFrage() {return bearbeiteteFrage;}
    public JButton getStartBtn() {return startBtn;}
    public void setBearbeiteteFrage(int nummer) {bearbeiteteFrage = nummer;}
    public int getSekunden() {return sekunden;}
    public void quizGame(){
        if (verwaltungPanel != null) {
            this.remove(verwaltungPanel);
        }
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
        // Mystery entfernen
        if (mysteryPanel != null) {
            this.remove(mysteryPanel);
        }
        // Verwaltung entfernen
        if (verwaltungPanel != null) {
            this.remove(verwaltungPanel);
        }
        // Timer stoppen
        if (timer != null) {
            timer.stop();
        }
        // Eingabefelder leeren
        if (eingabe != null) {
            eingabe.setText("");
        }
        if (frageEingabe != null) {
            frageEingabe.setText("");
        }
        if (antwortEingabe != null) {
            antwortEingabe.setText("");
        }
        // Menü anzeigen
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
    // Erstellung des Mysterymodus
    public void mysteryGame(){
        this.start.setVisible(false);
        if(this.fall != null){
            this.remove(fall);
        }
        if(this.quizPanel != null){
            this.remove(quizPanel);
        }
        if(this.mysteryPanel != null){
            this.remove(mysteryPanel);
        }
        mysteryPanel = new JPanel(new BorderLayout(10,10));
        JPanel oben = new JPanel(new BorderLayout());
        oben.setBackground(new Color(236,246,249));
        mysteryZeit = new JLabel("00:00");
        mysteryPunkte = new JLabel("Punkte: 0");
        mysteryZeit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        mysteryPunkte.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
        oben.add(mysteryZeit, BorderLayout.LINE_START);
        oben.add(mysteryPunkte, BorderLayout.LINE_END);
        mysteryPanel.add(oben, BorderLayout.PAGE_START);
        JLabel frage = new JLabel("Untersuche den ersten Hinweis!");
        mysteryPanel.add(frage, BorderLayout.CENTER);
        this.add(mysteryPanel);
        this.revalidate();
        this.repaint();
    }
    public void fallErklaerung(ActionListener listener){
        this.removeAll();
        fall = new JPanel(new BorderLayout(10,10));
        startBtn = new JButton("Ermittlung starten");
        JLabel header = new JLabel("Der Cyberangriff");
        header.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,28));
        fall.add(header, BorderLayout.PAGE_START);
        JTextArea beschreibung = new JTextArea("Am Montagmorgen funktioniert das Schulnetzwerk plötzlich nicht mehr richtig. " + "Die IT-Abteilung entdeckt Hinweise auf mehrere Cyberangriffe. Deine Aufgabe ist es, den Täter zu finden.");
        beschreibung.setEditable(false);
        beschreibung.setLineWrap(true);
        beschreibung.setWrapStyleWord(true);
        startBtn.setActionCommand("Ermittlung starten");
        startBtn.addActionListener(listener);
        fall.add(beschreibung,BorderLayout.CENTER);
        fall.add(startBtn,BorderLayout.PAGE_END);
        this.add(fall);
        this.revalidate();
        this.repaint();
    }
    // Erstellung der Verwaltung
    public void verwaltung(String[] fragen, String[] antworten, String[] bildfragen, String[] bildantworten, ActionListener controller) {
        this.start.setVisible(false);
        if (this.quizPanel != null) {
            this.remove(quizPanel);
        }
        if (this.mysteryPanel != null) {
            this.remove(mysteryPanel);
        }
        if (this.verwaltungPanel != null) {
            this.remove(verwaltungPanel);
        }
        // Verwaltung erstellen
        verwaltungPanel = new JPanel(new BorderLayout(5, 5));
        JLabel titel = new JLabel("Verwaltung");
        titel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        verwaltungPanel.add(titel, BorderLayout.PAGE_START);
        // Fragenliste
        fragenListe = new JPanel();
        fragenListe.setLayout(new GridLayout(0, 1, 5, 5));
        // Textfragen anzeigen
        for (int i = 0; i < fragen.length; i++) {
            JPanel fragePanel = new JPanel(new BorderLayout(10, 5));
            JTextArea frageLabel = new JTextArea("Frage: " + fragen[i] + "\nAntwort: " + antworten[i]);
            frageLabel.setEditable(false);
            frageLabel.setBackground(Color.WHITE);
            frageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            JButton bearbeitenBtn = new JButton("Bearbeiten");
            bearbeitenBtn.setActionCommand("Bearbeiten:" + i);
            buttonsGestalten(bearbeitenBtn);
            JButton loeschenBtn = new JButton("Löschen");
            buttonsGestalten(loeschenBtn);
            loeschenBtn.setActionCommand("Frage löschen:" + i);
            loeschenBtn.addActionListener(controller);
            final int nummer = i;
            bearbeitenBtn.addActionListener(e -> {
                bearbeiteteFrage = nummer;
                frageEingabe.setText(fragen[nummer]);
                antwortEingabe.setText(antworten[nummer]);
                speicheBtn.setText("Änderungen speichern");
            });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(bearbeitenBtn);
            buttonPanel.add(loeschenBtn);
            fragePanel.add(frageLabel, BorderLayout.CENTER);
            fragePanel.add(buttonPanel, BorderLayout.LINE_END);
            fragenListe.add(fragePanel);
        }
        // Bildfragen anzeigen
        for (int i = 0; i < bildfragen.length; i++) {
            JPanel fragePanel = new JPanel(new BorderLayout(10, 5));
            JTextArea frageLabel = new JTextArea("Bildfrage: " + bildfragen[i] + "\nAntwort: " + bildantworten[i]);
            frageLabel.setEditable(false);
            frageLabel.setBackground(Color.WHITE);
            frageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            JButton bearbeitenBtn = new JButton("Bearbeiten");
            bearbeitenBtn.setActionCommand("Bildfrage bearbeiten:" + i);
            buttonsGestalten(bearbeitenBtn);
            fragePanel.add(frageLabel, BorderLayout.CENTER);
            fragePanel.add(bearbeitenBtn, BorderLayout.LINE_END);
            fragenListe.add(fragePanel);
        }
        JScrollPane scrollPane = new JScrollPane(fragenListe);
        verwaltungPanel.add(scrollPane, BorderLayout.CENTER);
        // Eingabebereich
        JPanel eingabePanel = new JPanel(new BorderLayout(5, 5));
        JPanel felder = new JPanel(new GridLayout(2, 2, 5, 5));
        frageEingabe = new JTextArea(3, 30);
        antwortEingabe = new JTextField();
        felder.add(new JLabel("Frage:"));
        felder.add(new JScrollPane(frageEingabe));
        felder.add(new JLabel("Antwort:"));
        felder.add(antwortEingabe);
        eingabePanel.add(felder, BorderLayout.CENTER);
        // Buttons unten
        speicheBtn = new JButton("Speichern");
        speicheBtn.setActionCommand("Neue Frage speichern");
        buttonsGestalten(speicheBtn);
        backtomenu.setActionCommand("Zurück zum Menü");
        buttonsGestalten(backtomenu);
        JPanel untenButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        untenButtons.add(backtomenu);
        untenButtons.add(speicheBtn);
        eingabePanel.add(untenButtons, BorderLayout.LINE_END);
        verwaltungPanel.add(eingabePanel, BorderLayout.PAGE_END);
        // Verwaltung anzeigen
        this.add(verwaltungPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void buttonsGestalten(JButton btn){
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120,35));
    }
}
