package parsetree.intermednodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IIntermediateNode;
import parsetree.IdentifierList;
import parsetree.Utils;
import parsetree.mathnodes.Identifier;

public class OutNode extends AbstractParseTreeNode implements IIntermediateNode
{
    IdentifierList idList;

    public OutNode ()
    {
        super();

        idList = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.WRITE)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "write", true);
        tokenizer.skipToken();

        idList = new IdentifierList();
        idList.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ";", true);
        tokenizer.skipToken();
    }

    public void execute ()
    {
        for (Identifier i : idList.getIdentifiers())
            System.out.println(i.getName()+": "+i.eval());
    }

    public void print ()
    {
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("write ");
        idList.print();
        Utils.prettyPrintWrite(";\n");
    }
}
