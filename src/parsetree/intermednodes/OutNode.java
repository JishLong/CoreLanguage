package parsetree.intermednodes;

import parsetree.ErrorCheckingNode;
import parsetree.IIntermediateNode;
import parsetree.miscnodes.IdListNode;
import parsetree.Utils;
import parsetree.miscnodes.IdNode;

public class OutNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IdListNode idList;

    public OutNode ()
    {
        super("\"out\" statement");

        idList = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.WRITE)
            Utils.throwUnexpTokenError(tokenizer, "write", true);
        tokenizer.skipToken();

        idList = new IdListNode();
        idList.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer, ";", true);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("write ");
        idList.print();
        Utils.prettyPrintWrite(";\n");
    }

    public void execute ()
    {
        super.execute();

        for (IdNode i : idList.getIdentifiers())
            System.out.println(i.getName()+": "+i.eval());
    }
}
