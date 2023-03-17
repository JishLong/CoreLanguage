package parsetree.mathnodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class ExpNode extends AbstractParseTreeNode implements IMathNode
{
    private IMathNode fac, exp;
    private Utils.Token alternative;

    public ExpNode ()
    {
        super();

        fac = null;
        exp = null;
        alternative = null;
    }

    public void parse ()
    {
        fac = new FacNode();
        fac.parse();

        alternative = Utils.getToken(tokenizer.getToken());
        if (alternative == Utils.Token.PLUS || alternative == Utils.Token.MINUS)
        {
            tokenizer.skipToken();
            exp = new ExpNode();
            exp.parse();
        }
    }

    public int eval ()
    {
        int retVal = fac.eval();
        if (alternative == Utils.Token.PLUS)
            retVal += exp.eval();
        else if (alternative == Utils.Token.MINUS)
            retVal -= exp.eval();

        return retVal;
    }

    public void print ()
    {
        fac.print();
        if (exp != null)
        {
            if (alternative == Utils.Token.PLUS)
                Utils.prettyPrintWrite(" + ");
            else
                Utils.prettyPrintWrite(" - ");
            exp.print();
        }
    }
}
