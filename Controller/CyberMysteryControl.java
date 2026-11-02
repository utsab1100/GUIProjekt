package GUIProjekt.Controller;
import GUIProjekt.Model.CyberMystery;
import GUIProjekt.View.CyberMysteryFrame;
import GUIProjekt.View.CyberMysteryPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.sql.SQLOutput;

public class CyberMysteryControl implements ActionListener {
    private CyberMystery model;
    private CyberMysteryPanel view;
    private CyberMysteryFrame frame;
    private boolean bildModus = false;
    public CyberMysteryControl() {
        model = new CyberMystery();
        view = new CyberMysteryPanel();
        frame = new CyberMysteryFrame(view);
        view.getQuizbtn().addActionListener(this);
        view.getBacktomenu().addActionListener(this);
        view.getContinuebtn().addActionListener(this);
        view.getExitbtn().addActionListener(this);
        view.getVerwaltungsbtn().addActionListener(this);
        view.getMysterybtn().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        System.out.println("ActionCommand: " + ac);
        if (ac.equals("Quiz")) {
            this.model.quizReset();
            bildModus = false;
            this.view.quizGame();
            this.view.setAktuelleFrage(this.model.getAktuelleFrage());
            this.view.setPunkte(this.model.getPunkte());
            this.view.setFrageZaehler(1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
        } else if (ac.equals("Antwort prüfen")) {
            String eingabe = view.getEingabe().trim();
            if(eingabe.isEmpty()){
                JOptionPane.showMessageDialog(view,"Bitte gib eine Antwort ein!", "Leere Eingabe", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean richtig;
            if (!bildModus) {
                richtig = model.antwortPruefen(view.getEingabe());
            } else {
                richtig = model.antwortPruefenBild(view.getEingabe());
            }
            if (richtig) {
                model.richtigeAntwort();
            } else {
                model.falscheAntwort();
            }
            view.setPunkte(model.getPunkte());
            view.farbeAenderung(richtig);
            // Zuerst Textfragen
            if (!bildModus) {
                if (this.model.getAktuelleFrageNummer() < model.getAnzahlFragen() - 1) {
                    model.naechsteFrage();
                    view.setAktuelleFrage(model.getAktuelleFrage());
                    view.setFrageZaehler(model.getAktuelleFrageNummer() + 1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
                } else {
                    bildModus = true;
                    model.quizBildStarten();
                    view.zeigeBildfrage(model.getAktuelleBildfrage(), model.getBildaktuelleFrage());
                    view.setFrageZaehler(model.getAnzahlFragen() + 1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
                }
            } else {
                // Bildfragen
                if (model.getBildaktuelleFrage() < model.getAnzahlBildfragen() - 1) {
                    model.naechstesBild();
                    view.zeigeBildfrage(model.getAktuelleBildfrage(), model.getBildaktuelleFrage());
                    view.setFrageZaehler(model.getAnzahlFragen() + model.getBildaktuelleFrage() + 1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
                } else {
                    view.quizFertig();
                    view.statistikAnzeigen(model.getAnzahlRichtigeAntworten(), model.getFalscheAntworten(), view.getSekunden());
                    System.out.println("Quiz beendet –> Statistik wird geöffnet.");
                }
            }
        } else if (ac.equals("Zurück zum Menü")) {
            bildModus = false;
            this.view.menue();
        } else if(ac.equals("Beenden")){
            System.exit(0);
        } else if (ac.equals("Verwaltung")) {
            view.verwaltung(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(),this);
            view.getSpeicheBtn().addActionListener(this);
        } else if (ac.equals("Neue Frage speichern")) {
            String frage = view.getFrageEingabe().getText().trim();
            String antwort = view.getAntwortEingabe().getText().trim();
            if (!frage.isEmpty() && !antwort.isEmpty()) {
                if (view.getBearbeiteteFrage() == -1) {
                    model.frageHinzufuegen(frage, antwort);
                } else {
                    model.frageBearbeiten(view.getBearbeiteteFrage(), frage, antwort);
                    view.setBearbeiteteFrage(-1);
                }
                model.datenSpeichern();
                view.verwaltung(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(),this);
                view.getSpeicheBtn().addActionListener(this);
            }
        } else if (ac.startsWith("Frage löschen:")) {
            String nummerText = ac.substring(14);
            int nummer = Integer.parseInt(nummerText);
            int auswahl = JOptionPane.showConfirmDialog(view, "Frage wirklich löschen?", "Frage löschen", JOptionPane.YES_NO_OPTION);
            if (auswahl == JOptionPane.YES_OPTION) {
                model.frageLoeschen(nummer);
                model.datenSpeichern();
                view.verwaltung(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(), this);
                view.getSpeicheBtn().addActionListener(this);
            }
        } else if(ac.equals("Mystery")){
            System.out.println("Mystery Button wird gedrückt!");
            view.fallErklaerung(this);
        } else if (ac.equals("Ermittlung starten")) {
            System.out.println("Mystery Button wird gedrückt!");
            view.mysteryGame();
        }
    }
    public static void main(String[] args) {
        new CyberMysteryControl();
    }
}
