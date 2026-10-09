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
                "Wie nennt man den Angriff, durch gefälschte Emails das Passwort zu stehlen?", //1.Frage
                "Was bedeutet die Abkürzung MFA", //2.Frage
                "Welche Sicherheitsmaßnahme blockiert unerlaubte Eindringen und \nschützt die Netzwerksicherheit?",//3.Frage
                "Wie nennt man den Schadtsoftware, die die Dateien verschlüsselt \nund Lösegeld fordert?",//4.Frage
                "Wie nennt man die Manipulation, \num in die vertrauten Daten der Menschen zu gelangen",//5.Frage
                "Was bedeutet DDoS"//6.Frage
        };
        this.richtigeAntworten = new String[]{
                "Phishing",//1. Antwort
                "Multi-Faktor-Authentifizierung",//2. Antwort
                "Firewall",//3. Antwort
                "Ransomware",//4. Antwort
                "Social-Engineering",//5. Antwort
                "Distributed-Denial-of-Service"//6. Antwort
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
