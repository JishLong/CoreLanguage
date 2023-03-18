package parsetree.miscnodes;

import parsetree.ErrorCheckingNode;
import parsetree.Utils;

import java.util.ArrayList;
import java.util.List;

public class IdListNode extends ErrorCheckingNode
{
    private IdNode id;
    private IdListNode idList;

    public IdListNode ()
    {
        super("identifier list");

        id = null;
        idList = null;
    }

    public void parse ()
    {
        id = IdNode.parse();

        if (Utils.getToken(tokenizer.getToken()) == Utils.Token.COMMA)
        {
            tokenizer.skipToken();
            idList = new IdListNode();
            idList.parse();
        }

        super.parse();
    }

    public void print ()
    {
        super.print();

        id.print();
        if (idList != null)
        {
            Utils.prettyPrintWrite(", ");
            idList.print();
        }
    }

    // Returns a list of all the identifiers this identifier list "contains"
    public List<IdNode> getIdentifiers ()
    {
        super.execute();

        List<IdNode> identifiers;
        if (idList != null)
        {
            identifiers = idList.getIdentifiers();
            identifiers.add(0, id);
        }
        else
        {
            identifiers = new ArrayList<>();
            identifiers.add(id);
        }
        return identifiers;
    }
}
