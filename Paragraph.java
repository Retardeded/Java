import java.io.PrintStream;

public class Paragraph extends Section{

    String content;
    String title;

    Paragraph setContent(String content)
    {
        this.content = content;
        return this;
    }

    void writeHTML(PrintStream out)
    {
        String mark1 = "<p>";
        String mark2 = "</p>";
        this.content = mark1 + content + mark2;
        out.print(content);
    }

    Paragraph(String content) {
        this.content = content;
    }

    Paragraph(){

    }

}