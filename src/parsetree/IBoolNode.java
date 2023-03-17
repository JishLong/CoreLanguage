package parsetree;

public interface IBoolNode extends IParseTreeNode
{
    void parse();

    boolean eval();
}
