package parsetree.intermednodes;

import parsetree.*;
import parsetree.miscnodes.IdListNode;

public class DeclNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IdListNode idList;

    public DeclNode ()
    {
        super("variable declaration");

        idList = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.INT)
            Utils.throwUnexpTokenError(tokenizer, "int", true);
        tokenizer.skipToken();

        idList = new IdListNode();
        idList.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer, "\",\" or \";\"", false);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("int ");
        idList.print();
        Utils.prettyPrintWrite(";\n");
    }

    public void execute ()
    {
        super.execute();
    }
}
