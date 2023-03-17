package parsetree.intermednodes;

import parsetree.*;
import parsetree.boolnodes.CondNode;

public class LoopNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IBoolNode cond;
    private IIntermediateNode stmtSeq;

    public LoopNode ()
    {
        super();

        cond = null;
        stmtSeq = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.WHILE)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "while", true);
        tokenizer.skipToken();

        cond = new CondNode();
        cond.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.LOOP)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "loop", true);
        tokenizer.skipToken();

        stmtSeq = new StmtSeqNode();
        stmtSeq.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.END)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "end", true);
        tokenizer.skipToken();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ";", true);
        tokenizer.skipToken();
    }

    public void execute ()
    {
        while (cond.eval())
            stmtSeq.execute();
    }

    public void print ()
    {
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("while ");
        cond.print();
        Utils.prettyPrintWrite(" loop\n");
        Utils.prettyPrintIncreaseIndent();
        stmtSeq.print();
        Utils.prettyPrintDecreaseIndent();
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("end;\n");
    }
}
