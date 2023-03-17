package parsetree;

public class ComparisonOperator extends AbstractParseTreeNode
{
    private Utils.Token alternative;

    public ComparisonOperator ()
    {
        super();

        alternative = null;
    }

    public void parse ()
    {
        Utils.Token currentToken = Utils.getToken(tokenizer.getToken());

        if (currentToken == Utils.Token.NOTEQUALS || currentToken == Utils.Token.EQUALS ||
                currentToken == Utils.Token.LESSTHAN || currentToken == Utils.Token.GREATERTHAN ||
                currentToken == Utils.Token.LESSTHANEQUALS || currentToken == Utils.Token.GREATERTHANEQUALS)
            alternative = currentToken;
        else
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "comparison operator", false);

        tokenizer.skipToken();
    }

    public void print ()
    {
        switch (alternative)
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
            case GREATERTHANEQUALS:
                Utils.prettyPrintWrite(">=");
                break;
            default:
                break;
        }
    }

    public Utils.Token getOperator ()
    {
        return alternative;
    }
}
