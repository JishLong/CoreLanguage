package parsetree;

import tokenizer.Tokenizer;

// A node that most classes inherit from - contains manners to check for certain errors
public abstract class ErrorCheckingNode extends AbstractParseTreeNode
{
    protected final String nodeName;
    protected boolean isParsed;

    protected ErrorCheckingNode (String nodeName, Tokenizer tokenizer, String dataFileName)
    {
        super(tokenizer, dataFileName);

        this.nodeName = nodeName;
        isParsed = false;
    }

    protected ErrorCheckingNode (String nodeName)
    {
        super();

        this.nodeName = nodeName;
        isParsed = false;
    }

    protected void parse ()
    {
        isParsed = true;
    }

    protected void print ()
    {
        if (!isParsed)
            Utils.throwCustomError("Pretty printing error: incomplete parsing of "+nodeName);
    }

    protected void execute ()
    {
        if (!isParsed)
            Utils.throwCustomError("Execution error: incomplete parsing of "+nodeName);
    }
}
