package parsetree.boolnodes;

import parsetree.*;

public class CondNode extends ErrorCheckingNode implements ILogicNode
{
    private ILogicNode comp, cond1, cond2;
    private Utils.Token binaryCompOp;

    public CondNode ()
    {
        super("conditional expression");

        comp = cond1 = cond2 = null;
        binaryCompOp = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) == Utils.Token.PARENTHESELEFT)
        {
            comp = new CompNode();
            comp.parse();
        }
        else
        {
            Utils.Token firstToken = Utils.getToken(tokenizer.getToken());
            if (firstToken != Utils.Token.EXCLAMATION && firstToken != Utils.Token.SQUARELEFT)
                Utils.throwUnexpTokenError(tokenizer, null, false);
            tokenizer.skipToken();

            cond1 = new CondNode();
            cond1.parse();

            if (firstToken == Utils.Token.SQUARELEFT)
            {
                binaryCompOp = Utils.getToken(tokenizer.getToken());
                if (binaryCompOp != Utils.Token.AND && binaryCompOp != Utils.Token.OR)
                    Utils.throwUnexpTokenError(tokenizer, "\"&&\" or \"||\"", false);
                tokenizer.skipToken();

                cond2 = new CondNode();
                cond2.parse();

                if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SQUARERIGHT)
                    Utils.throwUnexpTokenError(tokenizer, "]", true);
                tokenizer.skipToken();
            }
        }

        super.parse();
    }

    public void print ()
    {
        super.print();

        if (comp != null)
            comp.print();
        else if (cond2 != null)
        {
            Utils.prettyPrintWrite("[");
            cond1.print();
            if (binaryCompOp == Utils.Token.AND)
                Utils.prettyPrintWrite(" && ");
            else
                Utils.prettyPrintWrite(" || ");
            cond2.print();
            Utils.prettyPrintWrite("]");
        }
        else
        {
            Utils.prettyPrintWrite("!");
            cond1.print();
        }
    }

    public boolean evaluate()
    {
        super.execute();

        if (comp != null)
            return comp.evaluate();

        if (cond2 != null)
        {
            if (binaryCompOp == Utils.Token.AND)
                return cond1.evaluate() && cond2.evaluate();
            else
                return cond1.evaluate() || cond2.evaluate();
        }

        return !cond1.evaluate();
    }
}
