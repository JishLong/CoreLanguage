package parsetree;

import parsetree.mathnodes.Identifier;

import java.util.ArrayList;
import java.util.List;

public class IdentifierList extends AbstractParseTreeNode
{
    private Identifier id;
    private IdentifierList idList;

    public IdentifierList ()
    {
        super();

        id = null;
        idList = null;
    }

    public void parse ()
    {
        id = new Identifier();
        id.parse();

        if (Utils.getToken(tokenizer.getToken()) == Utils.Token.COMMA)
        {
            tokenizer.skipToken();
            idList = new IdentifierList();
            idList.parse();
        }
    }

    public void print ()
    {
        id.print();
        if (idList != null)
        {
            Utils.prettyPrintWrite(", ");
            idList.print();
        }
    }

    public List<Identifier> getIdentifiers ()
    {
        List<Identifier> identifiers;

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
