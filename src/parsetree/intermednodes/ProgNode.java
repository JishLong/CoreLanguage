package parsetree.intermednodes;

import parsetree.ErrorCheckingNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;
import parsetree.miscnodes.IdNode;
import tokenizer.Tokenizer;

public class ProgNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IIntermediateNode declSeq, stmtSeq;

    public ProgNode (Tokenizer tokenizer, String dataFileName)
    {
        super("program structure", tokenizer, dataFileName);

        declSeq = stmtSeq = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PROGRAM)
            Utils.throwUnexpTokenError(tokenizer, "program", true);
        tokenizer.skipToken();

        declSeq = new DeclSeqNode();
        declSeq.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.BEGIN)
            Utils.throwUnexpTokenError(tokenizer, "begin", true);
        tokenizer.skipToken();

        IdNode.setDeclaringMode(false);

        stmtSeq = new StmtSeqNode();
        stmtSeq.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.END)
            Utils.throwUnexpTokenError(tokenizer, "end", true);
        tokenizer.skipToken();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.EOF)
            Utils.throwUnexpTokenError(tokenizer, null, false);

        tokenizer.close();

        super.parse();
    }

    public void print ()
    {
        super.print();

        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("program\n");
        Utils.prettyPrintIncreaseIndent();
        declSeq.print();
        Utils.prettyPrintDecreaseIndent();
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("begin\n");
        Utils.prettyPrintIncreaseIndent();
        stmtSeq.print();
        Utils.prettyPrintDecreaseIndent();
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("end\n");
    }

    public void execute ()
    {
        super.execute();

        declSeq.execute();
        stmtSeq.execute();

        try
        {
            if (dataFileReader != null)
                dataFileReader.close();
        }
        catch (Exception e) { Utils.throwCustomError(tokenizer, "Execution error closing data file"); }
    }
}
