package parsetree.intermednodes;

import parsetree.*;
import parsetree.boolnodes.CondNode;

public class IfNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IBoolNode cond;
    private IIntermediateNode stmtSeq1, stmtSeq2;

    public IfNode ()
    {
        super();

        cond = null;
        stmtSeq1 = null;
        stmtSeq2 = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.IF)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "if", true);
        tokenizer.skipToken();

        cond = new CondNode();
        cond.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.THEN)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "then", true);
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
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "end", true);
        tokenizer.skipToken();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.SEMICOLON)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ";", true);
        tokenizer.skipToken();
    }

    public void execute ()
    {
        if (cond.eval())
            stmtSeq1.execute();
        else if (stmtSeq2 != null)
            stmtSeq2.execute();
    }

    public void print ()
    {
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
}
