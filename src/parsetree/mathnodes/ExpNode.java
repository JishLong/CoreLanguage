package parsetree.mathnodes;

import parsetree.ErrorCheckingNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class ExpNode extends ErrorCheckingNode implements IMathNode
{
    private IMathNode fac, exp;
    private Utils.Token alternative;

    public ExpNode ()
    {
        super("expression");

        fac = exp = null;
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

        super.parse();
    }

    public void print ()
    {
        super.print();

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

    public int evaluate ()
    {
        super.execute();

        int retVal = fac.evaluate();
        if (alternative == Utils.Token.PLUS)
            retVal += exp.evaluate();
        else if (alternative == Utils.Token.MINUS)
            retVal -= exp.evaluate();
        return retVal;
    }
}
