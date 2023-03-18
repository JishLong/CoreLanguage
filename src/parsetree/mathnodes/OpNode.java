package parsetree.mathnodes;

import parsetree.ErrorCheckingNode;
import parsetree.IMathNode;
import parsetree.Utils;
import parsetree.miscnodes.IdNode;

public class OpNode extends ErrorCheckingNode implements IMathNode
{
    private IdNode id;
    private IMathNode mathNode;

    public OpNode ()
    {
        super("expression");

        id = null;
        mathNode = null;
    }

    public void parse ()
    {
        Utils.Token firstToken = Utils.getToken(tokenizer.getToken());

        switch (firstToken)
        {
            case INTEGER:
                mathNode = new IntNode();
                break;
            case IDENTIFIER:
                id = IdNode.parse();
                break;
            case PARENTHESELEFT:
                tokenizer.skipToken();
                mathNode = new ExpNode();
                break;
            default:
                Utils.throwUnexpTokenError(tokenizer, "expression", false);
                break;
        }

        if (mathNode != null)
            mathNode.parse();

        if (firstToken == Utils.Token.PARENTHESELEFT)
        {
            if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PARENTHESERIGHT)
                Utils.throwUnexpTokenError(tokenizer, ")", true);
            tokenizer.skipToken();
        }

        super.parse();
    }

    public void print ()
    {
        super.print();

        if (id != null)
            id.print();
        else if (mathNode instanceof ExpNode)
        {
            Utils.prettyPrintWrite("(");
            mathNode.print();
            Utils.prettyPrintWrite(")");
        }
        else
            mathNode.print();
    }

    public int evaluate()
    {
        super.execute();

        if (id != null)
            return id.eval();
        else
            return mathNode.evaluate();
    }
}
