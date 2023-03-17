package parsetree;

public class Utils
{
    public enum Token
    {
        PROGRAM, BEGIN, END, INT, IF, THEN, ELSE, WHILE, LOOP, READ, WRITE, SEMICOLON, COMMA,
        ASSIGN, EXCLAMATION, SQUARELEFT, SQUARERIGHT, AND, OR, PARENTHESELEFT, PARENTHESERIGHT, PLUS, MINUS, MULTIPLY,
        NOTEQUALS, EQUALS, LESSTHAN, GREATERTHAN, LESSTHANEQUALS, GREATERTHANEQUALS, INTEGER, IDENTIFIER, EOF, ERROR
    }

    private static final Token[] tokens = Token.values();
    private static int indentAmount = 0;

    // Returns the identifier of [token], given that [token] is a valid token in the Core language
    public static Token getToken (int token)
    {
        token--;
        if (token >= 0 && token < tokens.length - 1)
        {
            return tokens[token];
        }
        return Token.ERROR;
    }

    public static void throwUnexpTokenError (String unexpToken, String expToken, boolean useQuotesForExpToken)
    {
        if (expToken != null)
        {
            if (useQuotesForExpToken)
                System.err.println("Error: unexpected token \""+unexpToken+"\". Expected \""+expToken+"\"");
            else
                System.err.println("Error: unexpected token \""+unexpToken+"\". Expected "+expToken);
        }
        else
            System.err.println("Error: unexpected token \""+unexpToken+"\"");

        System.exit(1);
    }

    public static void throwUndecIdError (String id)
    {
        System.err.println("Error: identifier \""+id+"\" has not been declared");
        System.exit(1);
    }

    public static void throwExistIdError (String id)
    {
        System.err.println("Error: identifier \""+id+"\" has already been declared");
        System.exit(1);
    }

    public static void throwUninitIdError (String id)
    {
        System.err.println("Error: identifier \""+id+"\" has not been initialized");
        System.exit(1);
    }

    public static void throwCustomError (String errorMessage)
    {
        System.err.println(errorMessage);
        System.exit(1);
    }

    public static void prettyPrintWrite (String line)
    {
        if (line != null)
            System.out.print(line);
    }

    public static void prettyPrintIndent ()
    {
        for (int i = 0; i < indentAmount; i++)
            System.out.print("\t");
    }

    public static void prettyPrintIncreaseIndent ()
    {
        indentAmount++;
    }

    public static void prettyPrintDecreaseIndent ()
    {
        indentAmount--;
        if (indentAmount < 0)
            indentAmount = 0;
    }
}
