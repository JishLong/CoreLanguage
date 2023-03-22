package parsetree.intermednodes;

import parsetree.ErrorCheckingNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;
import static parsetree.Utils.Token.BEGIN;
import static parsetree.Utils.Token.EOF;

public class DeclSeqNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IIntermediateNode decl, declSeq;

    public DeclSeqNode ()
    {
        super("variable declaration");

        decl = declSeq = null;
    }

    public void parse ()
    {
        decl = new DeclNode();
        decl.parse();

        if (Utils.getToken(tokenizer.getToken()) != BEGIN && Utils.getToken(tokenizer.getToken()) != EOF)
        {
            declSeq = new DeclSeqNode();
            declSeq.parse();
        }

        super.parse();
    }

    public void print ()
    {
        super.print();

        decl.print();
        if (declSeq != null)
            declSeq.print();
    }

    public void execute ()
    {
        super.execute();
    }
}
