package parsetree.intermednodes;

import parsetree.*;
import parsetree.boolnodes.CondNode;

public class IfNode extends ErrorCheckingNode implements IIntermediateNode
{
    private ILogicNode cond;
    private IIntermediateNode stmtSeq1, stmtSeq2;

    public IfNode ()
    {
        super("\"if\" statement");

        cond = null;
        stmtSeq1 = stmtSeq2 = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.IF)
            Utils.throwUnexpTokenError(tokenizer, "if", true);
        tokenizer.skipToken();

        cond = new CondNode();
        cond.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.THEN)
            Utils.throwUnexpTokenError(tokenizer, "then", true);
        tokenizer.skipToken();

        stmtSeq1 = new StmtSeqNode();
        stmtSeq1.parse();

        if (Utils.getToken(tokenizer.getToken()) == Utils.Token.ELSE)
        {
            tokenizer.skipToken();
            stmtSeq2 = new StmtSeqNode();
            stmtSeq2.parse();
        }

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.END)
            Utils.throwUnexpTokenError(tokenizer, "end", true);
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
        Utils.prettyPrintWrite("if ");
        cond.print();
        Utils.prettyPrintWrite(" then\n");
        Utils.prettyPrintIncreaseIndent();
        stmtSeq1.print();
        Utils.prettyPrintDecreaseIndent();
        if (stmtSeq2 != null)
        {
            Utils.prettyPrintIndent();
            Utils.prettyPrintWrite("else\n");
            Utils.prettyPrintIncreaseIndent();
            stmtSeq2.print();
            Utils.prettyPrintDecreaseIndent();
        }
        Utils.prettyPrintIndent();
        Utils.prettyPrintWrite("end;\n");
    }

    public void execute ()
    {
        super.execute();

        if (cond.evaluate())
            stmtSeq1.execute();
        else if (stmtSeq2 != null)
            stmtSeq2.execute();
    }
}
