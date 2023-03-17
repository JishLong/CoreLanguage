package parsetree.intermednodes;

import parsetree.*;
import parsetree.mathnodes.ExpNode;
import parsetree.mathnodes.Identifier;

public class AssignNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private Identifier id;
    private IMathNode exp;

    AssignNode ()
    {
        super();

        id = null;
        exp = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.IDENTIFIER)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "identifier", false);

        id = new Identifier();
        id.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.ASSIGN)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "=", true);
        tokenizer.skipToken();

        exp = new ExpNode();
        exp.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ";", true);
        tokenizer.skipToken();
    }

    public void print ()
    {
        Utils.prettyPrintIndent();
        id.print();
        Utils.prettyPrintWrite(" = ");
        exp.print();
        Utils.prettyPrintWrite(";\n");
    }

    public void execute ()
    {
        id.setValue(exp.eval());
    }
}
