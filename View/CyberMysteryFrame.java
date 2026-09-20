package GUIProjekt.View;

import javax.swing.*;
public class CyberMysteryFrame extends JFrame {
    public CyberMysteryFrame(){
        this.add(new CyberMysteryPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    static void main() {
        new CyberMysteryFrame();
    }
}