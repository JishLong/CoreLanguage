package parsetree.intermednodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;

public class StmtNode extends AbstractParseTreeNode implements IIntermediateNode
{
    private IIntermediateNode actualStatement;

    StmtNode ()
    {
        super();

        actualStatement = null;
    }

    public void parse ()
    {
        switch (Utils.getToken(tokenizer.getToken()))
        {
            case IDENTIFIER:
                actualStatement = new AssignNode();
                break;
            case IF:
                actualStatement = new IfNode();
                break;
            case WHILE:
                actualStatement = new LoopNode();
                break;
            case READ:
                actualStatement = new InNode();
                break;
            case WRITE:
                actualStatement = new OutNode();
                break;
            default:
                Utils.throwUnexpTokenError(tokenizer.tokenVal(), null, false);
        }

        actualStatement.parse();
    }

    public void print ()
    {
        actualStatement.print();
    }

    public void execute ()
    {
        actualStatement.execute();
    }
}
