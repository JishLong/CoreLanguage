package parsetree.intermednodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IIntermediateNode;
import parsetree.IParseTreeNode;
import parsetree.Utils;

import static parsetree.Utils.Token.BEGIN;

public class DeclSeqNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IIntermediateNode decl, declSeq;

    DeclSeqNode ()
    {
        super();

        decl = null;
        declSeq = null;
    }

    public void parse ()
    {
        decl = new DeclNode();
        decl.parse();

        if (Utils.getToken(tokenizer.getToken()) != BEGIN)
        {
            declSeq = new DeclSeqNode();
            declSeq.parse();
        }
    }

    public void print ()
    {
        decl.print();
        if (declSeq != null)
            declSeq.print();
    }

    public void execute ()
    {
        // Nothing here :)
    }
}
