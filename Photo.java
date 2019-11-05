import java.io.*;

public class Photo extends Document{
    Photo(String url){
        this.url =url;
    }
    String url;
    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}