package GUIProjekt.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VerwaltungFrageVerwalten {
    private static String[] frageListe;

    /**
     * Liest alle Fragen aus der angegebenen Datei ein und speichert sie
     * in einem Array. Die einzelnen Fragen müssen durch ein Semikolon (;)
     * getrennt sein.
     *
     * @param fileURL Pfad zur Datei mit den Fragen
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    private static void refreshFrageListe(String fileURL) throws IOException {
        frageListe = Files.readString(Path.of(fileURL)).split(";");
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
     * Gibt die Frage an der angegebenen Position aus der Standarddatei
     * "assets/Fragen" zurück.
     *
     * @param index Position der gewünschten Frage im Array
     * @return Die Frage an der angegebenen Position
     * @throws IOException Falls die Datei nicht gelesen werden kann
     */
    public static String getFrage(int index) throws IOException {
        return getFrage(index, "assets/Fragen");
    }

    /**
     * Testmethode zum Ausgeben einer Frage in der Konsole.
     * Gibt die Frage mit dem Index 1 aus der Datei "assets/Fragen" aus.
     * Bei einem Lesefehler wird eine RuntimeException ausgelöst.
     */
    static void main() {
        try {
            System.out.println(getFrage(1, "assets/Fragen"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}