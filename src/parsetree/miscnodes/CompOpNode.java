package parsetree.miscnodes;

import parsetree.ErrorCheckingNode;
import parsetree.Utils;
import static parsetree.Utils.Token.*;

public class CompOpNode extends ErrorCheckingNode
{
    private Utils.Token operator;

    public CompOpNode ()
    {
        super("comparison operator");
        operator = null;
    }

    public void parse ()
    {
        Utils.Token currentToken = Utils.getToken(tokenizer.getToken());
        if (currentToken == NOTEQUALS || currentToken == EQUALS ||
                currentToken == LESSTHAN || currentToken == GREATERTHAN ||
                currentToken == LESSTHANEQUALS || currentToken == GREATERTHANEQUALS)
            operator = currentToken;
        else
            Utils.throwUnexpTokenError(tokenizer, nodeName, false);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

        switch (operator)
        {
            case NOTEQUALS:
                Utils.prettyPrintWrite("!=");
                break;
            case EQUALS:
                Utils.prettyPrintWrite("==");
                break;
            case LESSTHAN:
                Utils.prettyPrintWrite("<");
                break;
            case GREATERTHAN:
                Utils.prettyPrintWrite(">");
                break;
            case LESSTHANEQUALS:
                Utils.prettyPrintWrite("<=");
                break;
            default:
                Utils.prettyPrintWrite(">=");
                break;
        }
    }

    public Utils.Token getOperator ()
    {
        super.execute();

        return operator;
    }
}
