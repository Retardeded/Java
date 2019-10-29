public class Paragraph extends Section{

    String text;
    String title;

    void setContent(String text)
    {
        this.text = text;
    }

    void writeHTML(String text)
    {
        String mark1 = "<p>";
        String mark2 = "</p>";
        this.text = mark1 + text + mark2;
    }

    Paragraph(String title) {
        this.title = title;
    }
}
