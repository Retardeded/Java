import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section extends Document {
    String title;
    List<Paragraph> paragraps = new ArrayList<>() ;
    Section(String title)
    {
        this.title = title;
    }

    Section()
    {

    }

    Section setTitle(String title){
        Section section = new Section(title);
        return  section;
    }

    Section addParagraph(String paragraphText){
        Paragraph paragraph = new Paragraph(paragraphText);
        paragraps.add(paragraph);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraps.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        String mark1 = "<section>";
        String mark2 = "</section>";
    }
}
