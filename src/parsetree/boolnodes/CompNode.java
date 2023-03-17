package parsetree.boolnodes;

import parsetree.*;
import parsetree.mathnodes.OpNode;

public class CompNode extends AbstractParseTreeNode implements IBoolNode
{
    IMathNode op1, op2;
    ComparisonOperator compOp;

    public CompNode ()
    {
        super();

        op1 = null;
        op2 = null;
        compOp = null;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PARENTHESELEFT)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "(", true);
        tokenizer.skipToken();

        op1 = new OpNode();
        op1.parse();

        compOp = new ComparisonOperator();
        compOp.parse();

        op2 = new OpNode();
        op2.parse();

        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.PARENTHESERIGHT)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), ")", true);
        tokenizer.skipToken();
    }

    public boolean eval ()
    {
        switch (compOp.getOperator())
        {
            case NOTEQUALS:
                return op1.eval() != op2.eval();
            case EQUALS:
                return op1.eval() == op2.eval();
            case LESSTHAN:
                return op1.eval() < op2.eval();
            case GREATERTHAN:
                return op1.eval() > op2.eval();
            case LESSTHANEQUALS:
                return op1.eval() <= op2.eval();
            default:
                return op1.eval() >= op2.eval();
        }
    }

    public void print ()
    {
        Utils.prettyPrintWrite("(");
        op1.print();
        Utils.prettyPrintWrite(" ");
        compOp.print();
        Utils.prettyPrintWrite(" ");
        op2.print();
        Utils.prettyPrintWrite(")");
    }
}
