package parsetree.intermednodes;

import parsetree.*;
import parsetree.boolnodes.CondNode;

public class LoopNode extends ErrorCheckingNode implements IIntermediateNode
{
    private ILogicNode cond;
    private IIntermediateNode stmtSeq;

    public LoopNode ()
    {
        super("\"loop\" statement");

        cond = null;
        stmtSeq = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.WHILE)
            Utils.throwUnexpTokenError(tokenizer, "while", true);
        tokenizer.skipToken();

        cond = new CondNode();
        cond.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.LOOP)
            Utils.throwUnexpTokenError(tokenizer, "loop", true);
        tokenizer.skipToken();

        stmtSeq = new StmtSeqNode();
        stmtSeq.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.END)
            Utils.throwUnexpTokenError(tokenizer, "another statement or \"end\"", false);
        tokenizer.skipToken();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer, ";", true);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

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

    public void execute ()
    {
        super.execute();

        while (cond.evaluate())
            stmtSeq.execute();
    }
}
