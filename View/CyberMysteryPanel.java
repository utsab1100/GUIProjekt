package GUIProjekt.View;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
public class CyberMysteryPanel extends JPanel {
    private JButton mysterybtn, quizbtn, verwaltungsbtn, exitbtn;
    private JLabel header;
    private JPanel start;
    private QuizPanel quizPanel;
    private JPanel fall;
    private JButton startBtn;
    private MysteryPanel mysteryPanel;
    private JTextArea frageEingabe;
    private JTextField antwortEingabe;
    private JPanel fragenListe;
    private JButton speicheBtn;
    private int bearbeiteteFrage = -1;
    private VerwaltungPanel verwaltungPanel;
    public CyberMysteryPanel() {
        this.setLayout(new BorderLayout());
        // Hauptmenü
        start = new JPanel();
        start.setLayout(new BoxLayout(start, BoxLayout.PAGE_AXIS));
        start.setBackground(new Color(59, 77, 102));
        mysterybtn = new JButton("Mystery");
        quizbtn = new JButton("Quiz");
        verwaltungsbtn = new JButton("Verwaltung");
        exitbtn = new JButton("Beenden");
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
    public JButton getStartBtn() {return startBtn;}
    public QuizPanel getQuizPanel() {return quizPanel;}
    public VerwaltungPanel getVerwaltungPanel() {return verwaltungPanel;}
    public MysteryPanel getMysteryPanel() {return mysteryPanel;}
    public void menue() {
        // Mystery entfernen
        if (quizPanel != null) {
            quizPanel.timerStoppen();
            this.remove(quizPanel);
            quizPanel = null;
        }
        // Verwaltung entfernen
        if (verwaltungPanel != null) {
            this.remove(verwaltungPanel);
            verwaltungPanel = null;
        }
        if(mysteryPanel != null){
            this.remove(mysteryPanel);
            mysteryPanel = null;
        }
        if(fall != null){
            this.remove(fall);
            fall = null;
        }
        // Menü anzeigen
        this.removeAll();
        this.add(start,BorderLayout.CENTER);
        start.setVisible(true);
        this.revalidate();
        this.repaint();
    }
    public void quizAnzeigen(){
        this.removeAll();
        start.setVisible(false);
        this.quizPanel = new QuizPanel();
        this.quizPanel.quizGame();
        this.add(quizPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    public void verwaltungAnzeigen(String[] fragen, String[] antworten, String[] bildfragen, String[] bildantworten, ActionListener controller) {
        this.removeAll();
        start.setVisible(false);
        if (quizPanel != null) {
            quizPanel.timerStoppen();
            quizPanel = null;
        }
        verwaltungPanel = new VerwaltungPanel();
        verwaltungPanel.verwaltung(fragen, antworten, bildfragen, bildantworten, controller);
        this.add(verwaltungPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
    // Erstellung des Mysterymodus
    public void mysteryGame(){
        this.removeAll();
        start.setVisible(false);
        mysteryPanel = new MysteryPanel();
        mysteryPanel.mysteryGame();
        this.add(mysteryPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
