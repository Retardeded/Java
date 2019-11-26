import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    String[] current;

    boolean next() throws IOException {

        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        //

        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return false;
        }

        // podziel na pola
        String[] header = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        current = header;

        return true;
    }

    /*
     * @param filename  - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */



    public CSVReader(String filename) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filename));
    }

    public CSVReader(String filename,String delimiter) throws FileNotFoundException {

        this(filename);
        this.delimiter = delimiter;
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(filename,delimiter);
        this.hasHeader = hasHeader;
        if (hasHeader) parseHeader();
    }

    void parseHeader() throws IOException {
        // wczytaj wiersz
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
        }
        //...
    }

    List<String> getColumnLabels()
    {
        return columnLabels;
    }

    int getRecordLength()
    {
        return current.length;
    }

    boolean isMissing(int columnIndex)
    {
        return current[columnIndex] == null;
    }

    boolean isMissing(String columnLabel)
    {
        return current[columnLabelsToInt.get(columnLabel)] == null;
    }

    String get(int columnIndex)
    {
        if(!isMissing(columnIndex))
            return current[columnIndex];
        else
            return "";
    }

    String get(String columnLabel)
    {
        if(!isMissing(columnLabel))
            return current[columnLabelsToInt.get(columnLabel)];
        else
            return "";
    }

    int getInt(int columnIndex)
    {
        return Integer.parseInt(current[columnIndex]);
    }

    int getInt(String columnLabel)
    {
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }

    long getLong(int columnIndex)
    {
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel)
    {
        return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
    }

    double getDouble(int columnIndex)
    {
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel)
    {
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }

    LocalTime getTime(String colname) {
        return getTime(columnLabelsToInt.get(colname));
    }

    LocalTime getTime(int columnIndex) {
        return LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern("HH:mm"));
    }

    LocalDate getDate(String colname) {
        return getDate(columnLabelsToInt.get(colname));
    }

    LocalDate getDate(int columnIndex) {
        return LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}