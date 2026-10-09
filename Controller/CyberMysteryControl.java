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
    public CyberMysteryControl(){
        model = new CyberMystery();
        view = new CyberMysteryPanel();
        frame = new CyberMysteryFrame(view);
        view.getQuizbtn().addActionListener(this);
        view.getBacktomenu().addActionListener(this);
        view.getContinuebtn().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        if (ac.equals("Quiz")) {
            this.model.quizReset();
            this.view.quizGame();
            this.view.setAktuelleFrage(this.model.getAktuelleFrage());
            this.view.setPunkte(this.model.getPunkte());
        } else if(ac.equals("Antwort prüfen")){
            if(this.model.antwortPruefen(this.view.getEingabe())){
                this.model.richtigeAntwort();
                this.view.farbeAenderung(true);
            } else{
                this.model.falscheAntwort();
                this.view.farbeAenderung(false);
            }
            this.model.naechsteFrage();
            this.view.setAktuelleFrage(this.model.getAktuelleFrage());
            this.view.setPunkte(this.model.getPunkte());
        }else if(ac.equals("Zurück zum Menü")){
            this.view.menue();
        }
    }
    public static void main(String[] args){
        new CyberMysteryControl();
    }
}
