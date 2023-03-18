package parsetree.intermednodes;

import parsetree.ErrorCheckingNode;
import parsetree.IIntermediateNode;
import parsetree.miscnodes.IdListNode;
import parsetree.Utils;
import parsetree.miscnodes.IdNode;
import java.io.BufferedReader;
import java.io.FileReader;

public class InNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IdListNode idList;

    public InNode ()
    {
        super("\"in\" statement");

        idList = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.READ)
            Utils.throwUnexpTokenError(tokenizer, "read", true);
        tokenizer.skipToken();

        idList = new IdListNode();
        idList.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer, ";", true);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("read ");
        idList.print();
        Utils.prettyPrintWrite(";\n");
    }

    public void execute ()
    {
        super.execute();

        try
        {
            if (dataFileReader == null)
            {
                dataFileReader = new BufferedReader(new FileReader(dataFileName));
                if (!dataFileReader.ready())
                    Utils.throwCustomError("Execution error: data file empty when attempting read");
            }

            for (IdNode i : idList.getIdentifiers())
            {
                int newValue = Integer.parseInt(dataFileReader.readLine());
                i.setValue(newValue);
            }
        }
        catch (Exception e)
        {
            Utils.throwCustomError("Execution error reading from data file");
        }
    }
}
