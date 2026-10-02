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
    private String aktuelleFrage;
    private JLabel frage;
    private JPanel frageAnzeige;
    private JTextField eingabe;
    private int points=0;
    private JLabel punkte;
    private JLabel time;
    public CyberMysteryPanel() {
        this.setLayout(new BorderLayout());
        // Hauptmenü
        start = new JPanel();
        this.frageAnzeige = new JPanel();
        frage = new JLabel();
        this.frageAnzeige.setVisible(false);
        this.frage.setFont(new Font(Font.MONOSPACED,Font.BOLD,30));
        start.setLayout(new BoxLayout(start, BoxLayout.PAGE_AXIS));
        start.setBackground(new Color(59, 77, 102));
        mysterybtn = new JButton("Mystery");
        quizbtn = new JButton("Quiz");
        verwaltungsbtn = new JButton("Verwaltung");
        exitbtn = new JButton("Beenden");
        continuebtn = new JButton("Fortsetzen");
        backtomenu = new JButton("Zurück zum Menü");
        header = new JLabel("CYBER MYSTERY");
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        mysterybtn.setActionCommand("Mystery");
        quizbtn.setActionCommand("Quiz");
        verwaltungsbtn.setActionCommand("Verwaltung");
        exitbtn.setActionCommand("Beenden");
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
    public void quizGame(){
        this.points = 0;
        // Menü verstecken
        start.setVisible(false);
        quizPanel=new JPanel(new BorderLayout());
        // Obere Anzeige
        JPanel anzeigeOben=new JPanel(new GridLayout(1,2));
        JPanel punkteAnzeige=new JPanel(new FlowLayout(FlowLayout.LEFT));
        punkte=new JLabel("Punkte: "+points);
        punkteAnzeige.add(punkte);
        JPanel zeitAnzeige=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("HH:mm:ss");
        time=new JLabel("Zeit: "+LocalTime.now().format(formatter));
        zeitAnzeige.add(time);
        anzeigeOben.add(punkteAnzeige);
        anzeigeOben.add(zeitAnzeige);
        // Uhrzeit jede Sekunde aktualisieren
        Timer timer=new Timer(1000,e->{
            time.setText("Zeit: "+LocalTime.now().format(formatter));
        });
        this.frageAnzeige.setVisible(true);
        frageAnzeige.setLayout(new GridLayout(1,1));
        frage.setText(this.aktuelleFrage);
        this.eingabe=new JTextField();
        frageAnzeige.add(frage);
        timer.start();
        // Zurück-Button
        backtomenu.setActionCommand("Zurück zum Menü");
        JPanel unten=new JPanel();
        unten.add(backtomenu);
        quizPanel.add(anzeigeOben,BorderLayout.NORTH);
        quizPanel.add(unten,BorderLayout.SOUTH);
        quizPanel.add(frageAnzeige,BorderLayout.CENTER);
        this.add(quizPanel);
        this.revalidate();
        this.repaint();
    }
    public void menue() {
        // Quiz entfernen
        if (quizPanel != null) {
            this.remove(quizPanel);
        }
        // Menü wieder anzeigen
        start.setVisible(true);
        this.revalidate();
        this.repaint();
    }
    public void setAktuelleFrage(String aktuelleFrage){
        this.aktuelleFrage = aktuelleFrage;
    }
    public String getAktuelleFrage(){
        return this.aktuelleFrage;
    }
}