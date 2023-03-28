package parsetree;

// Represents any node that can be evaluated to obtain a numerical value
public interface IMathNode
{
    void parse();

    void print();

    int evaluate();
}
