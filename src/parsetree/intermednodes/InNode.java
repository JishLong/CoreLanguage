package parsetree.intermednodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IIntermediateNode;
import parsetree.IdentifierList;
import parsetree.Utils;
import parsetree.mathnodes.Identifier;

import java.io.BufferedReader;
import java.io.FileReader;

public class InNode extends AbstractParseTreeNode implements IIntermediateNode
{
    IdentifierList idList;

    public InNode ()
    {
        super();

        idList = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.READ)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "read", true);
        tokenizer.skipToken();

        idList = new IdentifierList();
        idList.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ";", true);
        tokenizer.skipToken();
    }

    public void execute ()
    {
        try
        {
            if (dataFileReader == null)
            {
                dataFileReader = new BufferedReader(new FileReader(dataFileName));
                if (!dataFileReader.ready())
                    Utils.throwCustomError("Error: data file empty");
            }

            for (Identifier i : idList.getIdentifiers())
            {
                int newValue = Integer.parseInt(dataFileReader.readLine());
                i.setValue(newValue);
            }
        }
        catch (Exception e)
        {
            Utils.throwCustomError("Error reading from data file");
        }
    }

    public void print ()
    {
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("read ");
        idList.print();
        Utils.prettyPrintWrite(";\n");
    }
}
