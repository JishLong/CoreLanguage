package parsetree;

import tokenizer.Tokenizer;

import java.io.BufferedReader;

public abstract class AbstractParseTreeNode implements IParseTreeNode
{
    protected static Tokenizer tokenizer;
    protected static String dataFileName;
    protected static BufferedReader dataFileReader;

    protected AbstractParseTreeNode (Tokenizer tokenizer, String dataFileName)
    {
        AbstractParseTreeNode.tokenizer = tokenizer;
        AbstractParseTreeNode.dataFileName = dataFileName;
        dataFileReader = null;
    }

    protected AbstractParseTreeNode ()
    {

    }
}
