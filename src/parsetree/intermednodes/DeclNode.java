package parsetree.intermednodes;

import parsetree.*;

public class DeclNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IdentifierList idList;

    DeclNode ()
    {
        super();

        idList = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.INT)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "int", true);
        tokenizer.skipToken();

        idList = new IdentifierList();
        idList.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ";", true);
        tokenizer.skipToken();
    }

    public void print ()
    {
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("int ");
        idList.print();
        Utils.prettyPrintWrite(";\n");
    }

    public void execute ()
    {
        // Nothing here :)
    }
}
