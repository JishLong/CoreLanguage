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

        System.out.println("PRINTING:\n");
        rootNode.print();
        System.out.println("\n\nEXECUTION:\n");
        rootNode.execute();

        /*int tokenIdentifier;

        do
        {
            if (tokenizer == null)
                tokenizer = new Tokenizer(args[0]);
            else
                tokenizer.skipToken();

            tokenIdentifier = tokenizer.getToken();

            // If the token is one of the normal 1 - 32 tokens or 33 (EOF), we want to print it
            if (tokenIdentifier > 0 && tokenIdentifier < 34)
                System.out.println(tokenIdentifier);

        }
        // Tokens 1 - 32 mean we aren't done yet
        while (tokenIdentifier > 0 && tokenIdentifier < 33);*/
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
