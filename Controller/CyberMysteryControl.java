package GUIProjekt.Controller;
import GUIProjekt.Model.CyberMystery;
import GUIProjekt.View.CyberMysteryFrame;
import GUIProjekt.View.CyberMysteryPanel;
import GUIProjekt.View.QuizPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;
public class CyberMysteryControl implements ActionListener {
    private CyberMystery model;
    private CyberMysteryPanel view;
    private CyberMysteryFrame frame;
    private QuizPanel quizView;
    private boolean bildModus = false;
    public CyberMysteryControl() {
        model = new CyberMystery();
        view = new CyberMysteryPanel();
        frame = new CyberMysteryFrame(view);
        model.datenLaden();
        view.getQuizbtn().addActionListener(this);
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
            view.quizAnzeigen();
            quizView = view.getQuizPanel();
            quizView.getContinuebtn().addActionListener(this);
            quizView.getBacktomenu().addActionListener(this);
            quizView.setAktuelleFrage(model.getAktuelleFrage());
            quizView.setPunkte(model.getPunkte());
            quizView.setFrageZaehler(1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
        } else if (ac.equals("Antwort prüfen")) {
            String eingabe = quizView.getEingabe().trim();
            if (eingabe.isEmpty()) {
                JOptionPane.showMessageDialog(quizView, "Bitte gib eine Antwort ein!", "Leere Eingabe", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean richtig;
            if (!bildModus) {
                richtig = model.antwortPruefen(eingabe);
            } else {
                richtig = model.antwortPruefenBild(eingabe);
            }
            if (richtig) {
                model.richtigeAntwort();
            } else {
                model.falscheAntwort();
            }
            quizView.setPunkte(model.getPunkte());
            quizView.farbeAenderung(richtig);
            // Zuerst alle Textfragen durchgehen
            if (!bildModus) {
                if (model.getAktuelleFrageNummer() < model.getAnzahlFragen() - 1) {
                    model.naechsteFrage();
                    quizView.setAktuelleFrage(model.getAktuelleFrage());
                    quizView.setFrageZaehler(model.getAktuelleFrageNummer() + 1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
                } else {
                    if (model.getAnzahlBildfragen() > 0) {
                        bildModus = true;
                        model.quizBildStarten();
                        quizView.zeigeBildfrage(model.getAktuelleBildfrage(), model.getBildaktuelleFrage());
                        quizView.setFrageZaehler(model.getAnzahlFragen() + 1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
                    } else {
                        quizView.quizFertig();
                        quizView.statistikAnzeigen(model.getAnzahlRichtigeAntworten(), model.getFalscheAntworten(), quizView.getSekunden());
                    }
                }
            } else {
                // Bildfragen
                if (model.getBildaktuelleFrage() < model.getAnzahlBildfragen() - 1) {
                    model.naechstesBild();
                    quizView.zeigeBildfrage(model.getAktuelleBildfrage(), model.getBildaktuelleFrage());
                    quizView.setFrageZaehler(model.getAnzahlFragen() + model.getBildaktuelleFrage() + 1, model.getAnzahlFragen() + model.getAnzahlBildfragen());
                } else {
                    quizView.quizFertig();
                    quizView.statistikAnzeigen(model.getAnzahlRichtigeAntworten(), model.getFalscheAntworten(), quizView.getSekunden());
                    System.out.println("Quiz beendet –> Statistik wird geöffnet.");
                }
            }
        } else if (ac.equals("Zurück zum Menü")) {
            bildModus = false;
            this.view.menue();
        } else if(ac.equals("Beenden")){
            System.exit(0);
        } else if (ac.equals("Verwaltung")) {
            model.datenLaden();
            view.verwaltungAnzeigen(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(), this);
        } else if (ac.equals("Neue Frage speichern") || ac.equals("Bildfrage speichern")) {
            String frage = view.getVerwaltungPanel().getFrageEingabe().getText().trim();
            String antwort = view.getVerwaltungPanel().getAntwortEingabe().getText().trim();
            if (frage.isEmpty() || antwort.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Bitte gib eine Frage und eine Antwort ein!", "Fehlende Eingabe", JOptionPane.WARNING_MESSAGE);
            } else {
                int nummer = view.getVerwaltungPanel().getBearbeiteteFrage();
                boolean bildfrage = view.getVerwaltungPanel().isBildfrageBearbeiten();
                if (bildfrage) {
                    model.bildfrageBearbeiten(nummer, frage, antwort);
                } else if (nummer == -1) {
                    model.frageHinzufuegen(frage, antwort);
                } else {
                    model.frageBearbeiten(nummer, frage, antwort);
                }
                view.getVerwaltungPanel().setBearbeiteteFrage(-1);
                model.datenSpeichern();
                view.verwaltungAnzeigen(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(), this);
            }
        } else if (ac.startsWith("Frage löschen:")) {
            String nummerText = ac.substring(14);
            int nummer = Integer.parseInt(nummerText);
            int auswahl = JOptionPane.showConfirmDialog(view, "Frage wirklich löschen?", "Frage löschen", JOptionPane.YES_NO_OPTION);
            if (auswahl == JOptionPane.YES_OPTION) {
                model.frageLoeschen(nummer);
                model.datenSpeichern();
                view.verwaltungAnzeigen(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(), this);
            }
        } else if (ac.startsWith("Bildfrage löschen:")) {
            String nummerText = ac.substring(19);
            int nummer = Integer.parseInt(nummerText);
            int auswahl = JOptionPane.showConfirmDialog(view, "Bildfrage wirklich löschen?", "Bildfrage löschen", JOptionPane.YES_NO_OPTION);
            if (auswahl == JOptionPane.YES_OPTION) {
                model.datenSpeichern();
                view.verwaltungAnzeigen(model.getFragen(), model.getRichtigeAntworten(), model.getBildfragen(), model.getRichtigeBildfragen(), this);
            }
        } else if (ac.equals("Mystery")) {
            System.out.println("Mystery Button wird gedrückt!");
            view.getMysteryPanel().fallErklaerung(this);
        } else if (ac.equals("Ermittlung starten")) {
            System.out.println("Mystery Button wird gedrückt!");
            view.mysteryGame();
        }
    }
    public static void main(String[] args) {
        new CyberMysteryControl();
    }
}
