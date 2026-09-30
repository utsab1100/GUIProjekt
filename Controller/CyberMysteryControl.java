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
        frame = new CyberMysteryFrame();
        view = new CyberMysteryPanel();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String ac = e.getActionCommand();
        if(ac.equals("Quiz")){
            this.view.quizGame(0);
        } else if(ac.equals("Zurück zum Menü")){
            this.view.menue();
        }
    }
}
