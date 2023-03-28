import parsetree.IIntermediateNode;
import parsetree.intermednodes.ProgNode;
import tokenizer.Tokenizer;

// Driver class for the tokenizer to be utilized
public class Program
{
    public static void main (String[] args)
    {
        checkCommandLineParams(args);

        Tokenizer tokenizer = new Tokenizer(args[0]);
        String dataFileName = null;
        if (args.length > 1)
            dataFileName = args[1];
        IIntermediateNode rootNode = new ProgNode(tokenizer, dataFileName);

        rootNode.parse();
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\nPRINTING:\n");
        rootNode.print();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\nEXECUTION:\n");
        rootNode.execute();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    // Does a quick check of the command line parameters to make sure they are satisfactory
    private static void checkCommandLineParams (String[] args)
    {
        if (args.length == 0)
        {
            System.err.println("Error: expected a file containing a Core program");
            System.exit(1);
        }
        else if (args.length > 2)
        {
            System.err.println("Error: too many parameters");
            System.exit(1);
        }
    }
}
