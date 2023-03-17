package parsetree;

public interface IMathNode extends IParseTreeNode
{
    void parse();

    int eval();
}
