package parsetree.mathnodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class FacNode extends AbstractParseTreeNode implements IMathNode
{
    private IMathNode op, fac;

    public FacNode ()
    {
        super();

        op = null;
        fac = null;
    }

    public void parse ()
    {
        op = new OpNode();
        op.parse();

        if (Utils.getToken(tokenizer.getToken()) == Utils.Token.MULTIPLY)
        {
            tokenizer.skipToken();
            fac = new FacNode();
            fac.parse();
        }
    }

    public int eval ()
    {
        int retVal = op.eval();
        if (fac != null)
            retVal *= fac.eval();

        return retVal;
    }

    public void print ()
    {
        op.print();
        if (fac != null)
        {
            Utils.prettyPrintWrite(" * ");
            fac.print();
        }
    }
}
