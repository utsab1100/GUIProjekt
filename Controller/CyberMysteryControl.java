package GUIProjekt.Controller;
import GUIProjekt.Model.CyberMystery;
import GUIProjekt.View.CyberMysteryFrame;
import GUIProjekt.View.CyberMysteryPanel;
import java.awt.event.ActionEvent;
import java.awt.event.*;
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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        if (ac.equals("Quiz")) {
            this.model.quizReset();
            bildModus = false;
            this.view.quizGame();
            this.view.setAktuelleFrage(this.model.getAktuelleFrage());
            this.view.setPunkte(this.model.getPunkte());
            this.view.setFrageZaehler(1,model.getAnzahlFragen()+model.getAnzahlBildfragen());
        } else if (ac.equals("Antwort prüfen")) {
            boolean richtig;
            if (!bildModus) {
                richtig = model.antwortPruefen(view.getEingabe());
            } else {
                richtig = model.antwortPruefenBild(view.getEingabe());
            }
            if(richtig) {
                model.richtigeAntwort();
            } else {
                model.falscheAntwort();
            }
            view.setPunkte(model.getPunkte());
            view.farbeAenderung(richtig);
            // Zuerst Textfragen
            if(!bildModus){
                if(this.model.getAktuelleFrageNummer() < model.getAnzahlFragen()-1){
                    model.naechsteFrage();
                    view.setAktuelleFrage(model.getAktuelleFrage());
                    view.setFrageZaehler(model.getAktuelleFrageNummer()+1,model.getAnzahlFragen()+model.getAnzahlBildfragen());
                } else{
                    bildModus = true;
                    model.quizBildStarten();
                    view.zeigeBildfrage(model.getAktuelleBildfrage(),model.getBildaktuelleFrage());
                    view.setFrageZaehler(model.getAnzahlFragen()+1,model.getAnzahlFragen()+model.getAnzahlBildfragen());
                }
            } else {
                // Bildfragen
                if(model.getBildaktuelleFrage() < model.getAnzahlBildfragen()-1){
                    model.naechstesBild();
                    view.zeigeBildfrage(model.getAktuelleBildfrage(), model.getBildaktuelleFrage());
                    view.setFrageZaehler(model.getAnzahlFragen() + model.getBildaktuelleFrage()+1, model.getAnzahlFragen()+model.getAnzahlBildfragen());
                } else{
                    view.quizFertig();
                }
            }
        } else if (ac.equals("Zurück zum Menü")) {
            bildModus = false;
            this.view.menue();
        } else if(ac.equals("Beenden")){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
        new CyberMysteryControl();
    }
}
