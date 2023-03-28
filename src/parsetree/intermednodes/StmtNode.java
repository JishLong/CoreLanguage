package parsetree.intermednodes;

import parsetree.ErrorCheckingNode;
import parsetree.IIntermediateNode;
import parsetree.Utils;

public class StmtNode extends ErrorCheckingNode implements IIntermediateNode
{
    private IIntermediateNode actualStatement;

    public StmtNode ()
    {
        super("statement");

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
            // Error is checked here to avoid a too-specific error being thrown in the child nodes
            default:
                Utils.throwUnexpTokenError(tokenizer, nodeName, false);
        }

        actualStatement.parse();

        super.parse();
    }

    public void print ()
    {
        super.print();

        actualStatement.print();
    }

    public void execute ()
    {
        super.execute();

        actualStatement.execute();
    }
}
