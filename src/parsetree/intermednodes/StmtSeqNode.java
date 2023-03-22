package parsetree.intermednodes;

import parsetree.ErrorCheckingNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;

import static parsetree.Utils.Token.EOF;

public class StmtSeqNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IIntermediateNode stmt, stmtSeq;

    public StmtSeqNode ()
    {
        super("statement");

        stmt = stmtSeq = null;
    }

    public void parse ()
    {
        stmt = new StmtNode();
        stmt.parse();

        Utils.Token currentToken = Utils.getToken(tokenizer.getToken());

        // The only two tokens that can follow a statement are [else] and [end]
        if (currentToken != Utils.Token.ELSE && currentToken != Utils.Token.END && Utils.getToken(tokenizer.getToken()) != EOF)
        {
            stmtSeq = new StmtSeqNode();
            stmtSeq.parse();
        }

        super.parse();
    }

    public void print ()
    {
        super.print();

        stmt.print();
        if (stmtSeq != null)
            stmtSeq.print();
    }

    public void execute ()
    {
        super.execute();

        stmt.execute();
        if (stmtSeq != null)
            stmtSeq.execute();
    }
}
