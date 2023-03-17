package parsetree.mathnodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class OpNode extends AbstractParseTreeNode implements IMathNode
{
    private IMathNode child;

    public OpNode ()
    {
        super();

        child = null;
    }

    public void parse ()
    {
        switch (Utils.getToken(tokenizer.getToken()))
        {
            case INTEGER:
                child = new UnsignedInt();
                break;
            case IDENTIFIER:
                child = new Identifier();
                break;
            case PARENTHESELEFT:
                tokenizer.skipToken();
                child = new ExpNode();
                break;
            default:
                Utils.throwUnexpTokenError(tokenizer.tokenVal(), "expression", false);
                break;
        }

        child.parse();

        if (child instanceof ExpNode)
        {
            if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PARENTHESERIGHT)
                Utils.throwUnexpTokenError(tokenizer.tokenVal(), ")", true);
            tokenizer.skipToken();
        }
    }

    public int eval ()
    {
        return child.eval();
    }

    public void print ()
    {
        if (child instanceof ExpNode)
        {
            Utils.prettyPrintWrite("(");
            child.print();
            Utils.prettyPrintWrite(")");
        }
        else
            child.print();
    }
}
