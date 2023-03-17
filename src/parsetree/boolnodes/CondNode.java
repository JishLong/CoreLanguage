package parsetree.boolnodes;

import parsetree.*;

public class CondNode extends AbstractParseTreeNode implements IBoolNode
{
    IBoolNode comp, cond1, cond2;
    Utils.Token binaryCompOp;

    public CondNode ()
    {
        super();

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
            Utils.Token currentToken = Utils.getToken(tokenizer.getToken());
            if (currentToken != Utils.Token.EXCLAMATION && currentToken != Utils.Token.SQUARELEFT)
                Utils.throwUnexpTokenError(tokenizer.tokenVal(), null, false);
            tokenizer.skipToken();

            cond1 = new CondNode();
            cond1.parse();

            if (currentToken == Utils.Token.SQUARELEFT)
            {
                binaryCompOp = Utils.getToken(tokenizer.getToken());
                if (binaryCompOp != Utils.Token.AND && binaryCompOp != Utils.Token.OR)
                    Utils.throwUnexpTokenError(tokenizer.tokenVal(), "\"&&\" or \"||\"", false);
                tokenizer.skipToken();

                cond2 = new CondNode();
                cond2.parse();

                if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SQUARERIGHT)
                    Utils.throwUnexpTokenError(tokenizer.tokenVal(), "]", true);
                tokenizer.skipToken();
            }
        }
    }

    public boolean eval ()
    {
        if (comp != null)
            return comp.eval();

        if (cond2 != null)
        {
            if (binaryCompOp == Utils.Token.AND)
                return cond1.eval() && cond2.eval();
            else
                return cond1.eval() || cond2.eval();
        }

        return !cond1.eval();
    }

    public void print ()
    {
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
}
