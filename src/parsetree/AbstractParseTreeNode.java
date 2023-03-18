package parsetree;

import tokenizer.Tokenizer;
import java.io.BufferedReader;

// The root class for every parse tree node; contains a tokenizer and data file that all nodes have access to
public abstract class AbstractParseTreeNode
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
