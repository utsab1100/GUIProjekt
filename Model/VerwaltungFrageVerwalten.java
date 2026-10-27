package GUIProjekt.Model;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class VerwaltungFrageVerwalten {
    private static String[] frageListe;
    private static String standardURL = "assets/Fragen";

    /**
     * Liest alle Fragen aus der angegebenen Datei ein und speichert sie
     * in einem Array. Die einzelnen Fragen müssen durch die Zeichenfolge
     * "  " gefolgt von einem Zeilenumbruch getrennt sein.
     *
     * @param fileURL Pfad zur Datei mit den Fragen
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    private static void refreshFrageListe(String fileURL) throws IOException {
        frageListe = Files.readString(Path.of(fileURL)).replace(System.lineSeparator(), "").split("  ");
    }

    /**
     * Gibt die Frage an der angegebenen Position aus der angegebenen Datei zurück.
     * Vor dem Zugriff wird die Fragenliste neu aus der Datei eingelesen.
     *
     * @param index Position der gewünschten Frage im Array
     * @param fileURL Pfad zur Datei mit den Fragen
     * @return Die Frage an der angegebenen Position
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String getFrage(int index, String fileURL) throws IOException {
        refreshFrageListe(fileURL);
        return frageListe[index];
    }

    /**
     * Gibt *ie Frage an der angegebenen Position aus der aktuell
     * festgelegten Standarddatei zurück.
     *
     * @param index Position der gewünschten Frage im Array
     * @return Die Frage an der angegebenen Position
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String getFrage(int index) throws IOException {
        return getFrage(index, standardURL);
    }

    /**
     * Fügt eine neue Frage am Ende der angegebenen Datei hinzu.
     * Die Frage wird mit dem verwendeten Trennzeichen in die Datei geschrieben.
     *
     * @param frage Die hinzuzufügende Frage
     * @param fileURL Pfad zur Zieldatei
     * @throws IOException Falls nicht in die Datei geschrieben werden kann
     */
    public static void addFrage(String frage, String fileURL) throws IOException {
        Files.writeString(Path.of(fileURL), System.lineSeparator() + frage + "  ", StandardOpenOption.APPEND);
    }


    /**
     * Fügt *ine neue Frage am Ende der aktuellen Standarddatei hinzu*
     *
     * @param frage Die hinzuzufügende Frage
     * @throws IOException Falls nicht in die Datei geschrieben werden kann
     */
    public static void addFrage(String frage) throws IOException {
        addFrage(frage, standardURL);
    }

    /**
     * Löscht die Frage an der angegebenen Position aus der angegebenen Datei.
     * Dazu werden zunächst alle Fragen eingelesen, die Datei geleert und
     * anschließend alle Fragen außer der ausgewählten Frage wieder in die
     * Datei geschrieben. Danach wird die Fragenliste aktualisiert.
     *
     * @param index Position der zu löschenden Frage
     * @param fileURL Pfad zur Datei mit den Fragen
     * @throws IOException Falls die Datei nicht gelesen oder beschrieben
     *                     werden kann
     */
    public static void deleteFrage(int index, String fileURL) throws IOException {
        refreshFrageListe(fileURL);
        Path path = Path.of(fileURL);
        Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
        Files.writeString(path,  frageListe[0] + "  ", StandardOpenOption.APPEND);
        for (int i = 1; i < frageListe.length; i++) {
            if (i != index) {
                Files.writeString(path,  System.lineSeparator() + frageListe[i] + "  ", StandardOpenOption.APPEND);
            }
        }
        refreshFrageListe(fileURL);
    }

    /**
     * Löscht die Frage an der angegebenen Position aus der aktuell
     * festgelegten Standarddatei.
     *
     * @param index Position der zu löschenden Frage
     * @throws IOException Falls die Datei nicht gelesen oder beschrieben
     *                     werden kann
     */
    public static void deleteFrage(int index) throws IOException {
        deleteFrage(index, standardURL);
    }

    /**
     * Testmethode zum Ausgeben einer Frage in der Konsole.
     * Gibt die Frage mit dem Index 1 aus der Datei "assets/Fragen" aus.
     * Bei einem Lesefehler wird eine RuntimeException ausgelöst.
     */
    static void main() {
        try {
            System.out.println(getFrage(1, "assets/Fragen"));
            deleteFrage(5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStandardURL() {
        return standardURL;
    }

    public static void setStandardURL(String standardURL) {
        VerwaltungFrageVerwalten.standardURL = standardURL;
    }
}