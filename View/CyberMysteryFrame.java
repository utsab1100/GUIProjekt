package GUIProjekt.View;
import GUIProjekt.Model.CyberMystery;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.io.File;
public class CyberMysteryFrame extends JFrame {
    public CyberMysteryFrame(CyberMysteryPanel view){
        this.setTitle("Cyber Mystery");
        this.setSize(1200,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(view);
        this.setVisible(true);
    }
}