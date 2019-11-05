
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ParagraphWithList extends Paragraph{

    UnorderedList unorderedList;

    ParagraphWithList() {

    }

    public ParagraphWithList(String text, UnorderedList list) {
        this.content = text;
        this.unorderedList = list;
    }

    ParagraphWithList setContent(String text)
    {

        return this;
    }

    void writeHTML(PrintStream out)
    {
        String mark1 = "<p>";
        String mark2 = "</p>";
        this.content = mark1 + content + mark2;
        out.print(content);
        unorderedList.writeHTML(out);
    }

    ParagraphWithList addListItem(String newItem) {
        var newItems = new UnorderedList();
        newItems.add(newItem);
        return new ParagraphWithList(content, newItems);
    }

}


// mrcszk

