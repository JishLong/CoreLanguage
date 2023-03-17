package parsetree.intermednodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;

public class StmtSeqNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IIntermediateNode stmt, stmtSeq;

    StmtSeqNode ()
    {
        super();

        stmt = null;
        stmtSeq = null;
    }

    public void parse ()
    {
        stmt = new StmtNode();
        stmt.parse();

        Utils.Token currentToken = Utils.getToken(tokenizer.getToken());

        if (currentToken != Utils.Token.ELSE && currentToken != Utils.Token.END)
        {
            stmtSeq = new StmtSeqNode();
            stmtSeq.parse();
        }
    }

    public void print ()
    {
        stmt.print();
        if (stmtSeq != null)
            stmtSeq.print();
    }

    public void execute ()
    {
        stmt.execute();
        if (stmtSeq != null)
            stmtSeq.execute();
    }
}
