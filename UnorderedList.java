import java.io.PrintStream;
import java.util.*;

public class UnorderedList {
    List<ListItem> list = new ArrayList<>() ;
    UnorderedList(){};
    UnorderedList(String content){
        ListItem li = new ListItem(content);
        list.add(li);
    }
    public void addItem(String content){
        ListItem li = new ListItem(content);
        list.add(li);
    }
    public void writeHTML(PrintStream out){
        out.print("<ul>\n");
        for(ListItem x:list)
            x.writeHTML(out);
        out.print("\n</ul>");
    }
}