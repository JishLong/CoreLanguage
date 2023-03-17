package parsetree.intermednodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;
import parsetree.mathnodes.Identifier;
import tokenizer.Tokenizer;

public class ProgNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IIntermediateNode declSeq, stmtSeq;

    public ProgNode (Tokenizer tokenizer, String dataFileName)
    {
        super(tokenizer, dataFileName);

        declSeq = null;
        stmtSeq = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PROGRAM)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "program", true);
        tokenizer.skipToken();

        declSeq = new DeclSeqNode();
        declSeq.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.BEGIN)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "begin", true);
        tokenizer.skipToken();

        Identifier.disableDeclaring();

        stmtSeq = new StmtSeqNode();
        stmtSeq.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.END)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "end", true);
        tokenizer.skipToken();
    }

    public void print ()
    {
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
        declSeq.execute();
        stmtSeq.execute();
    }
}
