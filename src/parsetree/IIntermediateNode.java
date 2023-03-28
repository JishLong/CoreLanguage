package parsetree;

// Represents most nodes that are not evaluated to obtain a value
public interface IIntermediateNode
{
    void parse();

    void print();

    void execute();
}
