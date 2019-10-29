import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        // ???
        photo.url = photoUrl;
        return this;
    }

    Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section section = new Section(sectionTitle);
        sections.add(section);
        return section;
    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        String mark1 = "<body>";
        String mark2 = "</body>";


    }

}
