import java.io.PrintStream;
import java.util.*;

public class UnorderedList extends  ParagraphWithList{

    private final ArrayList<ListItem> list = new ArrayList<>();


    void writeHTML(PrintStream out)
    {
        String ul1 = "<ul>\n";
        String ul2 = "</ul>\n";
        String li1 = "<li>";
        String li2 = "</li>\n";

        out.print(ul1);
        for (ListItem item : list) {
            out.print(ul1 + item.text + ul2);
        }
        out.print(ul2);
    }

    public void add(String item)
    {
        list.add(new ListItem((item)));
    }

}
