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
    private String[] bildfragen;
    private String[] richtigeBildfragen;
    private int bildaktuelleFrage;
    public CyberMystery(){
        this.leben = 3;
        this.punkte = 0;
        this.aktuelleFrage = 0;
        this.bildaktuelleFrage = 0;
        this.anzahlRichtigeAntworten = 0;
        this.falscheAntworten = 0;
        this.hinweisFreigeschaltet = false;
        this.fragen = new String[]{
                "Wie nennt man den Angriff, durch gefälschte Emails das Passwort zu stehlen?", //1.Frage
                "Wofür steht die Abkürzung MFA", //2.Frage
                "Welche Sicherheitsmaßnahme blockiert unerlaubte Zugriffe auf ein Netzwerk?",//3.Frage
                "Wie nennt man Schadsoftware, die Dateien verschlüsselt und Lösegeld fordert?",//4.Frage
                "Wie nennt man die Manipulation von Menschen, um vertrauliche Informationen zu erhalten?",//5.Frage
                "Was bedeutet DDoS",//6.Frage
                "Wie nennt man eine Sicherheitskopie von wichtigen Daten?",//7. Frage
                "Wie nennt man das Programm, das heimlich Informationen sammelt?", // 8. Frage
        };
        this.richtigeAntworten = new String[]{
                "Phishing",//1. Antwort
                "Multi-Faktor-Authentifizierung",//2. Antwort
                "Firewall",//3. Antwort
                "Ransomware",//4. Antwort
                "Social-Engineering",//5. Antwort
                "Distributed-Denial-of-Service",//6. Antwort
                "Backup",//7. Antwort
                "Spyware"//8. Antwort
        };
        this.bildfragen = new String[]{
                "Was für eine Cyberattacke ist auf dem Bild zu sehen?",//9.Frage
                "Welche Cyberattacke ist auf dem Bild sichtbar?",//10.Frage
                "Was für eine Cyberattacke führt der Verdächtige auf?"//11.Frage
        };
        this.richtigeBildfragen = new String[]{
                "Man-in-the-middle",//9.Antwort
                "DDoS-Angriff",//10.Antwort
                "DNS poisoning"//11.Antwort
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
        return this.richtigeAntworten[this.aktuelleFrage].equalsIgnoreCase(antwort.trim());
    }
    public boolean antwortPruefenBild(String antwort){
        return this.richtigeBildfragen[bildaktuelleFrage].equalsIgnoreCase(antwort.trim());
    }
    public void richtigeAntwort(){
        this.punkte += 10;
        this.anzahlRichtigeAntworten++;
    }
    public void falscheAntwort(){
        if(this.punkte > 0){
            this.punkte -= 10;
        } else {
            this.punkte = 0;
        }
        this.falscheAntworten++;
    }
    public void lebenVerlieren(){
        if(this.leben > 0){
            this.leben--;
        }
    }
    public void hinweisFreischalten(){
        this.hinweisFreigeschaltet = true;
    }
    public void naechsteFrage(){
        if(this.aktuelleFrage < this.fragen.length-1){
            this.aktuelleFrage++;
        }
    }
    public void naechstesBild(){
        if(this.bildaktuelleFrage < this.bildfragen.length-1){
            this.bildaktuelleFrage++;
        }
    }
    public void quizReset(){
        this.aktuelleFrage = 0;
        this.bildaktuelleFrage = 0;
        this.punkte = 0;
        this.leben = 3;
        this.anzahlRichtigeAntworten = 0;
        this.falscheAntworten = 0;
        this.hinweisFreigeschaltet = false;
    }
    public void quizBildStarten(){
        this.bildaktuelleFrage = 0;
    }
    public String getAktuelleBildfrage(){
        return bildfragen[bildaktuelleFrage];
    }
    public int getBildaktuelleFrage() {
        return bildaktuelleFrage;
    }
    public int getAktuelleFrageNummer(){
        return aktuelleFrage;
    }
    public int getAnzahlFragen(){
        return fragen.length;
    }
    public int getAnzahlBildfragen(){
        return bildfragen.length;
    }
}
