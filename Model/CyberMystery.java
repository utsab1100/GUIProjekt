package GUIProjekt.Model;
public class CyberMystery {
    private int punkte;
    private int leben;
    private String[] fragen;
    private String[] richtigeAntworten;
    private int aktuelleFrage;
    private int anzahlRichtigeAntworten;
    private int falscheAntworten;
    private String[] hinweise;
    private boolean hinweisFreigeschaltet;
    public CyberMystery(){
        this.leben = 3;
        this.punkte = 0;
        this.aktuelleFrage = 0;
        this.anzahlRichtigeAntworten = 0;
        this.falscheAntworten = 0;
        this.hinweisFreigeschaltet = false;
        this.fragen = new String[]{
                "Wofür wird eine Firewall verwendet?",
                "Was bedeutet die Abkürzung MFA",
                "Was ist ein Computervirus"
        };
        this.richtigeAntworten = new String[]{
                "Zum Schutz vor unerlaubten Netzwerkzugriffen",
                "Multi-Faktor-Authentifizierung",
                "Ein schädliches Programm"
        };
    }
    public int getPunkte() {
        return punkte;
    }
    public int getLeben() {
        return leben;
    }
    public int getAnzahlRichtigeAntworten() {
        return anzahlRichtigeAntworten;
    }
    public int getFalscheAntworten() {
        return falscheAntworten;
    }
    public String[] getHinweise() {
        return hinweise;
    }
    public String getAktuelleFrage() {
        return fragen[aktuelleFrage];
    }
    public boolean antwortPruefen(String antwort){
        if(this.richtigeAntworten[this.aktuelleFrage].equalsIgnoreCase(antwort)){
            return true;
        }
        return false;
    }
    public void richtigeAntwort(){
        this.punkte += 10;
        this.anzahlRichtigeAntworten++;
    }
    public void falscheAntwort(){
        this.punkte -= 10;
        this.falscheAntworten++;
    }
    public void lebenVerlieren(){
        this.leben--;
    }
    public void hinweisFreischalten(){
        this.hinweisFreigeschaltet=true;
    }
    public void naechsteFrage(){
        if(this.aktuelleFrage< this.fragen.length-1){
            this.aktuelleFrage++;
        }
    }
    public void quizReset(){
        this.aktuelleFrage=0;
        this.punkte=0;
        this.anzahlRichtigeAntworten=0;
        this.falscheAntworten=0;
    }
}
