package parsetree.boolnodes;

import parsetree.*;
import parsetree.mathnodes.OpNode;
import parsetree.miscnodes.CompOpNode;

public class CompNode extends ErrorCheckingNode implements ILogicNode
{
    private IMathNode op1, op2;
    private CompOpNode compOp;

    public CompNode ()
    {
        super("boolean comparison");

        op1 = null;
        op2 = null;
        compOp = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PARENTHESELEFT)
            Utils.throwUnexpTokenError(tokenizer, "(", true);
        tokenizer.skipToken();

        op1 = new OpNode();
        op1.parse();

        compOp = new CompOpNode();
        compOp.parse();

        op2 = new OpNode();
        op2.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PARENTHESERIGHT)
            Utils.throwUnexpTokenError(tokenizer, ")", true);
        tokenizer.skipToken();

        super.parse();
    }

    public void print ()
    {
        super.print();

        Utils.prettyPrintWrite("(");
        op1.print();
        Utils.prettyPrintWrite(" ");
        compOp.print();
        Utils.prettyPrintWrite(" ");
        op2.print();
        Utils.prettyPrintWrite(")");
    }

    public boolean evaluate ()
    {
        super.execute();

        switch (compOp.getOperator())
        {
            case NOTEQUALS:
                return op1.evaluate() != op2.evaluate();
            case EQUALS:
                return op1.evaluate() == op2.evaluate();
            case LESSTHAN:
                return op1.evaluate() < op2.evaluate();
            case GREATERTHAN:
                return op1.evaluate() > op2.evaluate();
            case LESSTHANEQUALS:
                return op1.evaluate() <= op2.evaluate();
            default:
                return op1.evaluate() >= op2.evaluate();
        }
    }
}
