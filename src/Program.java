import tokenizer.Tokenizer;

// Driver class for the tokenizer to be utilized
public class Program
{
    public static void main (String[] args)
    {
        Tokenizer tokenizer = null;
        int tokenIdentifier;

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
        while (tokenIdentifier > 0 && tokenIdentifier < 33);
    }
}
