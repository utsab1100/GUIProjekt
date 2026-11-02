package GUIProjekt.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Verwaltet Fragen und Antworten, die in einer Textdatei gespeichert sind.
 *
 * Die Klasse stellt Funktionen zum Einlesen, Hinzufügen und Entfernen von
 * Einträgen bereit. Fragen und Antworten werden intern aus einer Datei
 * geladen und anhand definierter Markierungen verarbeitet.
 */
public class FrageVerwaltung {
    /**
     * Enthält die aktuell geladenen Fragen.
     */
    private static String[] frageListe;

    /**
     * Enthält die aktuell geladenen Antworten.
     */
    private static String[] antwortListe;

    /**
     * Standardpfad zur verwendeten Fragendatei.
     */
    private static String standardURL = "assets/Fragen";

    /**
     * Kennzeichnung für Antworteinträge innerhalb der Datei.
     */
    private static String antwortMarkierung = "<antwort> ";

    /**
     * Kennzeichnung für Frageeinträge innerhalb der Datei.
     */
    private static String frageMarkierung = "<frage> ";

    /**
     * Liest alle Fragen und Antworten aus der angegebenen Datei ein und
     * aktualisiert die internen Listen.
     *
     * @param fileURL Pfad zur Quelldatei
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    private static void refreshListen(String fileURL) throws IOException {
        String file = Files.readString(Path.of(fileURL));
        String[] eintraege = file.replace(System.lineSeparator(), "").split(frageMarkierung);

        String[] frageAntwort;
        frageListe = new String[eintraege.length - 1];
        antwortListe = new String[eintraege.length - 1];
        for (int i = 0; i < eintraege.length - 1; i++) {
            frageAntwort = eintraege[i + 1].split(antwortMarkierung);
            frageListe[i] = frageAntwort[0];
            if (frageAntwort.length > 1) {
                antwortListe[i] = frageAntwort[1];
            } else {
                antwortListe[i] = null;
            }
        }
    }

    /**
     * Gibt eine Kopie der aktuell gespeicherten Fragen aus der angegebenen
     * Datei zurück.
     *
     * @param fileURL Pfad zur Quelldatei
     * @return Kopie der Fragenliste
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String[] getFrageListe(String fileURL) throws IOException {
        refreshListen(fileURL);
        return frageListe.clone();
    }

    /**
     * Gibt eine Kopie der Fragenliste aus der Standarddatei zurück.
     *
     * @return Kopie der Fragenliste
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String[] getFrageListe() throws IOException {
        return getFrageListe(standardURL);
    }

    /**
     * Gibt eine Kopie der aktuell gespeicherten Antworten aus der angegebenen
     * Datei zurück.
     *
     * @param fileURL Pfad zur Quelldatei
     * @return Kopie der Antwortliste
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String[] getAntwortListe(String fileURL) throws IOException {
        refreshListen(fileURL);
        return antwortListe.clone();
    }

    /**
     * Gibt eine Kopie der Antwortliste aus der Standarddatei zurück.
     *
     * @return Kopie der Antwortliste
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String[] getAntwortListe() throws IOException {
        return getAntwortListe(standardURL);
    }

    /**
     * Fügt einen neuen Frage-Antwort-Eintrag an das Ende der angegebenen Datei
     * an.
     *
     * @param frage Die zu speichernde Frage
     * @param antwort Die zugehörige Antwort
     * @param fileURL Pfad zur Zieldatei
     * @throws IOException Falls der Eintrag nicht gespeichert werden kann
     */
    public static void addFrage(String frage, String antwort, String fileURL) throws IOException {
        String formatierung = System.lineSeparator() + System.lineSeparator() + frageMarkierung +
                frage + System.lineSeparator() + antwortMarkierung + antwort;
        Files.writeString(Path.of(fileURL), formatierung, StandardOpenOption.APPEND);
    }


    /**
     * Fügt einen neuen Frage-Antwort-Eintrag an die Standarddatei an.
     *
     * @param frage Die zu speichernde Frage
     * @param antwort Die zugehörige Antwort
     * @throws IOException Falls der Eintrag nicht gespeichert werden kann
     */
    public static void addFrage(String frage, String antwort) throws IOException {
        addFrage(frage, antwort, standardURL);
    }

    /**
     * Entfernt den Eintrag an der angegebenen Position aus der Datei und
     * erstellt den verbleibenden Datenbestand neu.
     *
     * @param index Index des zu entfernenden Eintrags
     * @param fileURL Pfad zur Zieldatei
     * @throws IOException Falls die Datei nicht gelesen oder beschrieben werden kann
     */
    public static void deleteFrage(int index, String fileURL) throws IOException {
        refreshListen(fileURL);
        Path path = Path.of(fileURL);
        Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
        if (index != 0) {
            Files.writeString(path, System.lineSeparator() + System.lineSeparator() + frageMarkierung +
                    frageListe[0] + System.lineSeparator() + antwortMarkierung + antwortListe[0], StandardOpenOption.APPEND);
        }
        for (int i = 1; i < frageListe.length; i++) {
            if (i != index) {
                Files.writeString(path,  System.lineSeparator() + System.lineSeparator() + frageMarkierung +
                        frageListe[i] + System.lineSeparator() + antwortMarkierung + antwortListe[i], StandardOpenOption.APPEND);
            }
        }
        refreshListen(fileURL);
    }

    /**
     * Entfernt den Eintrag an der angegebenen Position aus der Standarddatei.
     *
     * @param index Index des zu entfernenden Eintrags
     * @throws IOException Falls die Datei nicht gelesen oder beschrieben werden kann
     */
    public static void deleteFrage(int index) throws IOException {
        deleteFrage(index, standardURL);
    }

    /**
     * Führt einen einfachen Test der grundlegenden Verwaltungsfunktionen aus.
     */
    static void main() {
        try {
            System.out.println(getFrageListe("assets/Fragen")[0]);
            System.out.println(getFrageListe("assets/Fragen")[1]);
            addFrage("neue Frage", "eine Antwort");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gibt den aktuellen Standardpfad zurück.
     *
     * @return Standardpfad zur Fragendatei
     */
    public static String getStandardURL() {
        return standardURL;
    }

    /**
     * Legt den Standardpfad für zukünftige Zugriffe fest.
     *
     * @param standardURL Neuer Standardpfad
     */
    public static void setStandardURL(String standardURL) {
        FrageVerwaltung.standardURL = standardURL;
    }

    /**
     * Gibt die aktuelle Antwortmarkierung zurück.
     *
     * @return Antwortmarkierung
     */
    public static String getAntwortMarkierung() {
        return antwortMarkierung;
    }

    /**
     * Legt die Markierung für Antworteinträge fest.
     *
     * @param antwortMarkierung Neue Antwortmarkierung
     */
    public static void setAntwortMarkierung(String antwortMarkierung) {
        FrageVerwaltung.antwortMarkierung = antwortMarkierung;
    }

    /**
     * Gibt die aktuelle Fragemarkierung zurück.
     *
     * @return Fragemarkierung
     */
    public static String getFrageMarkierung() {
        return frageMarkierung;
    }

    /**
     * Legt die Markierung für Frageeinträge fest.
     *
     * @param frageMarkierung Neue Fragemarkierung
     */
    public static void setFrageMarkierung(String frageMarkierung) {
        FrageVerwaltung.frageMarkierung = frageMarkierung;
    }
}