package parsetree.mathnodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class UnsignedInt extends AbstractParseTreeNode implements IMathNode
{
    private int value;

    public UnsignedInt ()
    {
        super();
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.INTEGER)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "unsigned integer", false);

        value = tokenizer.intVal();

        tokenizer.skipToken();
    }

    public int eval ()
    {
        return value;
    }

    public void print ()
    {
        Utils.prettyPrintWrite(""+value);
    }
}
