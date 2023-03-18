package parsetree.mathnodes;

import parsetree.ErrorCheckingNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class FacNode extends ErrorCheckingNode implements IMathNode
{
    private IMathNode op, fac;

    public FacNode ()
    {
        super("expression");

        op = fac = null;
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

        super.parse();
    }

    public void print ()
    {
        super.print();

        op.print();
        if (fac != null)
        {
            Utils.prettyPrintWrite(" * ");
            fac.print();
        }
    }

    public int evaluate()
    {
        super.execute();

        int retVal = op.evaluate();
        if (fac != null)
            retVal *= fac.evaluate();
        return retVal;
    }
}
