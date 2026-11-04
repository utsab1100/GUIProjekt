
package GUIProjekt.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VerwaltungPanel extends JPanel {

    private JTextArea frageEingabe;
    private JTextField antwortEingabe;
    private JPanel fragenListe;
    private JButton speicheBtn;
    private int bearbeiteteFrage = -1;
    private boolean bildfrageBearbeiten = false;
    public VerwaltungPanel() {
        this.setLayout(new BorderLayout(5, 5));
    }
    public JButton getSpeicheBtn() {return speicheBtn;}
    public JTextArea getFrageEingabe() {return frageEingabe;}
    public JTextField getAntwortEingabe() {return antwortEingabe;}
    public int getBearbeiteteFrage() {return bearbeiteteFrage;}
    public void setBearbeiteteFrage(int nummer) {this.bearbeiteteFrage = nummer;}
    public boolean isBildfrageBearbeiten() {return bildfrageBearbeiten;}
    // Erstellung der Verwaltung
    public void verwaltung(String[] fragen, String[] antworten, String[] bildfragen, String[] bildantworten, ActionListener controller) {
        this.removeAll();
        // Verwaltung erstellen
        JLabel titel = new JLabel("Verwaltung");
        titel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        this.add(titel, BorderLayout.PAGE_START);
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
            buttonsGestalten(bearbeitenBtn);
            JButton loeschenBtn = new JButton("Löschen");
            buttonsGestalten(loeschenBtn);
            loeschenBtn.setActionCommand("Frage löschen:" + i);
            loeschenBtn.addActionListener(controller);
            final int nummer = i;
            bearbeitenBtn.addActionListener(e -> {
                this.bearbeiteteFrage = nummer;
                this.bildfrageBearbeiten = false;
                this.frageEingabe.setText(fragen[nummer]);
                this.antwortEingabe.setText(antworten[nummer]);
                this.speicheBtn.setText("Änderungen speichern");
                this.speicheBtn.setActionCommand("Neue Frage speichern");
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
            final int nummer = i;
            JPanel fragePanel = new JPanel(new BorderLayout(10, 5));
            JTextArea frageLabel = new JTextArea("Bildfrage: " + bildfragen[i] + "\nAntwort: " + bildantworten[i]);
            frageLabel.setEditable(false);
            frageLabel.setBackground(Color.WHITE);
            frageLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            JButton bearbeitenBtn = new JButton("Bearbeiten");
            buttonsGestalten(bearbeitenBtn);
            bearbeitenBtn.addActionListener(e -> {
                this.bearbeiteteFrage = nummer;
                this.bildfrageBearbeiten = true;
                this.frageEingabe.setText(bildfragen[nummer]);
                this.antwortEingabe.setText(bildantworten[nummer]);
                this.speicheBtn.setText("Bildfrage ändern");
                this.speicheBtn.setActionCommand("Bildfrage speichern");
            });
            JButton loeschenBtn = new JButton("Löschen");
            loeschenBtn.setActionCommand("Bildfrage löschen:" + i);
            buttonsGestalten(loeschenBtn);
            loeschenBtn.addActionListener(controller);
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(bearbeitenBtn);
            buttonPanel.add(loeschenBtn);
            fragePanel.add(frageLabel, BorderLayout.CENTER);
            fragePanel.add(buttonPanel, BorderLayout.LINE_END);
            fragenListe.add(fragePanel);
        }
        JScrollPane scrollPane = new JScrollPane(fragenListe);
        this.add(scrollPane, BorderLayout.CENTER);
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
        speicheBtn.addActionListener(controller);
        JButton zurueckBtn = new JButton("Zurück zum Menü");
        zurueckBtn.setActionCommand("Zurück zum Menü");
        buttonsGestalten(zurueckBtn);
        zurueckBtn.addActionListener(controller);
        JPanel untenButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        untenButtons.add(zurueckBtn);
        untenButtons.add(speicheBtn);
        eingabePanel.add(untenButtons, BorderLayout.PAGE_END);
        this.add(eingabePanel, BorderLayout.PAGE_END);
        // Verwaltung anzeigen
        this.revalidate();
        this.repaint();
    }
    public void buttonsGestalten(JButton btn) {
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(150, 35));
    }
}
