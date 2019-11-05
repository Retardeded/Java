
import java.io.*;
import java.util.ArrayList;
import java.util.List;

    public class Document {
        String title;
        Photo photo;
        List<Section> sections = new ArrayList<>();

        public Document(String s) {
            this.title = s;
        }

        public Document() {
        }

        Document setTitle(String title){
            this.title = title;
            return this;
        }

        Document setPhoto(String photoUrl){
            // ???
            photo.url = photoUrl;
            return this;
        }

        private void addPhoto(String s) {
            photo = new Photo(s);
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
            //setTitle("Jan Kowalski");
            //setPhoto("http://www.birds.com/wp-content/uploads/home/bird4.jpg");

            String html1 = "<?xml version=\"1.0\"?>\n" +
                    "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                    "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
            String html2 = "</html>";
            String head = "\n<head>" +
                    "\t<title>CV</title>\n" +
                    "\t<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;\n" +
                    "\t\tcharset=UTF-8\" />\n" +
                    "</head>\n";
            String body1 = "<body>\n";
            String body2 = "</body>\n";
            String h1 = "<div><h1>"+title+"</h1></div>\n";

            out.printf(html1);
            out.printf(head);
            out.printf(body1);
            out.printf(h1);
            photo.writeHTML(out);
            for (Section section : sections) {
                section.writeHTML(out);
            }
            out.printf(body2);
            out.printf(html2);

        }

        public static void main(String[] args) {
            Document cv = new Document("Jana Kowalski - CV");
            cv.addPhoto("http://www.birds.com/wp-content/uploads/home/bird4.jpg");
            cv.addSection("Wykształcenie")
                    .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                    .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                    .addParagraph("...");
            cv.addSection("Umiejętności")
                    .addParagraph(
                            new ParagraphWithList().setContent("Umiejętności")
                                    .addListItem("C")
                                    .addListItem("C++")
                                    .addListItem("Java")
                    );

            cv.writeHTML(System.out);
        }

    }


