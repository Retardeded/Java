import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String args[]) throws IOException {
        CSVReader reader = null;
        try {
            reader = new CSVReader("with-header.csv", ";", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (reader.next()) {
            int id = reader.getInt(0);
            String name = reader.get(1);
            String surname = reader.get("nazwisko");
            System.out.printf("%d %s %s\n", id, name, surname);

        }

        LocalTime time = LocalTime.parse("12:55", DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(time);
        time = LocalTime.parse("12:55:23", DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time);

        LocalDate date = LocalDate.parse("2017-11-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
        date = LocalDate.parse("23.11.2017", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(date);


    }

}
