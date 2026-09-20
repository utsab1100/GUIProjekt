package GUIProjekt.View;

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel.*;

public class CyberMysteryPanel extends JPanel {
    private JButton mysterybtn, quizbtn, verwaltungsbtn, exitbtn, continuebtn, backtomenu;
    private JLabel header;
    private JPanel start;
    public CyberMysteryPanel(){
        start = new JPanel();
        start.setLayout(new BoxLayout(start, BoxLayout.PAGE_AXIS));

        this.setLayout(new BorderLayout());
        mysterybtn = new JButton("Mystery");
        quizbtn = new JButton("Quiz");
        verwaltungsbtn = new JButton("Verwaltung");
        exitbtn = new JButton("Beenden");
        continuebtn = new JButton("Fortsetzen");
        backtomenu = new JButton("Zurück zum Menü");
        header = new JLabel("CYBER MYSTERY");
        Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        mysterybtn.setActionCommand("Mystery");
        mysterybtn.setFont(buttonFont);
        quizbtn.setActionCommand("Quiz");
        quizbtn.setFont(buttonFont);
        verwaltungsbtn.setActionCommand("Verwaltung");
        verwaltungsbtn.setFont(buttonFont);
        exitbtn.setActionCommand("Beenden");
        exitbtn.setFont(buttonFont);
        continuebtn.setActionCommand("Fortsetzen");
        backtomenu.setActionCommand("Zurück zum Menü");
        header.setFont(new Font(Font.MONOSPACED, Font.BOLD, 112));
        header.setForeground(new Color(238,238,238));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        mysterybtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        verwaltungsbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setAlignmentY(Component.CENTER_ALIGNMENT);
        int buttonwidth = 400;
        int buttonheight = 80;
        Color buttonColor = new Color(255,255,255);
        mysterybtn.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        mysterybtn.setBackground(buttonColor);
        quizbtn.setPreferredSize(new Dimension(buttonwidth,buttonheight));
        quizbtn.setBackground(buttonColor);
        verwaltungsbtn.setPreferredSize(new Dimension(buttonwidth,buttonheight));
        verwaltungsbtn.setBackground(buttonColor);
        exitbtn.setPreferredSize(new Dimension(buttonwidth, buttonheight));
        exitbtn.setBackground(buttonColor);
        mysterybtn.setMaximumSize(new Dimension(buttonwidth,buttonheight));
        quizbtn.setMaximumSize(new Dimension(buttonwidth,buttonheight));
        verwaltungsbtn.setMaximumSize(new Dimension(buttonwidth,buttonheight));
        exitbtn.setMaximumSize(new Dimension(buttonwidth,buttonheight));
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
        start.setBackground(new Color(59,77,102));
    }
    public void quizGame(){

    }
}
