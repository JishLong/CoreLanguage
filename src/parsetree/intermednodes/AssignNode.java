package parsetree.intermednodes;

import parsetree.*;
import parsetree.mathnodes.ExpNode;
import parsetree.miscnodes.IdNode;

public class AssignNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IdNode id;
    private IMathNode exp;

    public AssignNode ()
    {
        super("\"assignment\" statement");

        id = null;
        exp = null;
    }

    public void parse ()
    {
        id = IdNode.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.ASSIGN)
            Utils.throwUnexpTokenError(tokenizer, "=", true);
        tokenizer.skipToken();

        exp = new ExpNode();
        exp.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer, ";", true);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

        Utils.prettyPrintIndent();
        id.print();
        Utils.prettyPrintWrite(" = ");
        exp.print();
        Utils.prettyPrintWrite(";\n");
    }

    public void execute ()
    {
        super.execute();

        id.setValue(exp.evaluate());
    }
}
