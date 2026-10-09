package GUIProjekt.View;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
public class CyberMysteryPanel extends JPanel {
    private JButton mysterybtn, quizbtn, verwaltungsbtn, exitbtn;
    private JButton continuebtn, backtomenu;
    private JLabel header;
    private JPanel start;
    private JPanel quizPanel;
    private JLabel frage;
    private JPanel frageAnzeige;
    private JTextField eingabe;
    private JLabel punkte;
    private JLabel time;
    private JPanel anzeigeOben;
    private JPanel unten;
    public CyberMysteryPanel() {
        this.setLayout(new BorderLayout());
        // Hauptmenü
        start = new JPanel();
        this.frageAnzeige = new JPanel();
        this.anzeigeOben = new JPanel(new BorderLayout());
        frage = new JLabel();
        this.frageAnzeige.setVisible(false);
        this.frage.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        start.setLayout(new BoxLayout(start, BoxLayout.PAGE_AXIS));
        start.setBackground(new Color(59, 77, 102));
        mysterybtn = new JButton("Mystery");
        quizbtn = new JButton("Quiz");
        verwaltungsbtn = new JButton("Verwaltung");
        exitbtn = new JButton("Beenden");
        continuebtn = new JButton("Antwort prüfen");
        backtomenu = new JButton("Zurück zum Menü");
        header = new JLabel("CYBER MYSTERY");
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
        // Menü verstecken
        start.setVisible(false);
        quizPanel=new JPanel(new BorderLayout());
        quizPanel.setBackground(new Color(57,158,207));
        // Obere Anzeige
        anzeigeOben.setBackground(new Color(236,246,249));
        JPanel punkteAnzeige=new JPanel(new FlowLayout(FlowLayout.LEFT));
        punkte=new JLabel("Punkte: 0");
        punkte.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
        punkte.setForeground(Color.BLACK);
        punkteAnzeige.add(punkte);
        JPanel zeitAnzeige=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        time=new JLabel("Zeit: "+LocalTime.now().format(formatter));
        time.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));
        time.setForeground(Color.BLACK);
        anzeigeOben.add(punkte,BorderLayout.WEST);
        anzeigeOben.add(zeitAnzeige,BorderLayout.EAST);
        // Uhrzeit jede Sekunde aktualisieren
        Timer timer=new Timer(1000,e->{
            time.setText("Zeit: "+LocalTime.now().format(formatter));
        });
        timer.start();
        this.frageAnzeige.setVisible(true);
        frageAnzeige.setLayout(new BoxLayout(frageAnzeige,BoxLayout.Y_AXIS));
        frageAnzeige.setBackground(new Color(59,77,102));
        frage.setFont(new Font(Font.SANS_SERIF,Font.BOLD,32));
        frage.setForeground(Color.BLACK);
        frage.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.eingabe=new JTextField();
        frageAnzeige.removeAll();
        eingabe.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
        eingabe.setMaximumSize(new Dimension(700,60));
        eingabe.setAlignmentX(Component.CENTER_ALIGNMENT);
        continuebtn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,22));
        continuebtn.setSize(new Dimension(250,60));
        continuebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        frageAnzeige.add(Box.createVerticalGlue());
        frageAnzeige.add(frage);
        frageAnzeige.add(Box.createVerticalStrut(50));
        frageAnzeige.add(eingabe);
        frageAnzeige.add(Box.createVerticalStrut(30));
        frageAnzeige.add(continuebtn);
        frageAnzeige.add(Box.createVerticalGlue());
        // Zurück-Button
        backtomenu.setActionCommand("Zurück zum Menü");
        backtomenu.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
        unten=new JPanel(new FlowLayout(FlowLayout.CENTER));
        unten.setBackground(new Color(236,246,249));
        unten.add(backtomenu);
        quizPanel.add(anzeigeOben,BorderLayout.NORTH);
        quizPanel.add(unten,BorderLayout.SOUTH);
        quizPanel.add(frageAnzeige,BorderLayout.CENTER);
        this.add(quizPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void menue() {
        // Quiz entfernen
        if (quizPanel != null) {
            this.remove(quizPanel);
        }
        if(eingabe!=null){
            eingabe.setText("");
        }
        // Menü wieder anzeigen
        start.setVisible(true);
        this.revalidate();
        this.repaint();
    }
    public void setAktuelleFrage(String aktuelleFrage){
        this.frage.setText(aktuelleFrage);
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
}