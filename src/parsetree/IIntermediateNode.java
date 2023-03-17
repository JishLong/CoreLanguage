package parsetree;

public interface IIntermediateNode extends IParseTreeNode
{
    void parse();

    void execute();
}
