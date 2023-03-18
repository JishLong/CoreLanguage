package parsetree;

// Represents any node that can be evaluated to obtain a boolean value
public interface ILogicNode
{
    void parse();

    void print();

    boolean evaluate();
}
