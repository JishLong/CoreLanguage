package parsetree.mathnodes;

import parsetree.ErrorCheckingNode;
import parsetree.IMathNode;
import parsetree.Utils;

public class IntNode extends ErrorCheckingNode implements IMathNode
{
    private int value;

    public IntNode ()
    {
        super("unsigned integer");
        value = 0;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.INTEGER)
            Utils.throwUnexpTokenError(tokenizer, nodeName, false);
        value = tokenizer.intVal();
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();
        Utils.prettyPrintWrite(""+value);
    }

    public int evaluate ()
    {
        super.execute();
        return value;
    }
}
