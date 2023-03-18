package parsetree;

import tokenizer.Tokenizer;

// A collection of useful methods that are used throughout the parsetree package
public class Utils
{
    public enum Token
    {
        PROGRAM, BEGIN, END, INT, IF, THEN, ELSE, WHILE, LOOP, READ, WRITE, SEMICOLON, COMMA,
        ASSIGN, EXCLAMATION, SQUARELEFT, SQUARERIGHT, AND, OR, PARENTHESELEFT, PARENTHESERIGHT, PLUS, MINUS, MULTIPLY,
        NOTEQUALS, EQUALS, LESSTHAN, GREATERTHAN, LESSTHANEQUALS, GREATERTHANEQUALS, INTEGER, IDENTIFIER, EOF, ERROR
    }

    private static final Token[] tokens = Token.values();
    // The current number of indentations that the pretty printer is formatted to use for each new line it prints
    private static int indentAmount = 0;

    // Returns the identifier of [token], given that [token] is a valid token in the Core language
    public static Token getToken (int token)
    {
        token--;
        if (token >= 0 && token < tokens.length - 1)
            return tokens[token];
        return Token.ERROR;
    }

    // Prints an error message for an unexpected token and exits the program
    public static void throwUnexpTokenError (Tokenizer t, String expToken, boolean useQuotesForExpToken)
    {
        String tokenClassification;
        if (getToken(t.getToken()) == Token.ERROR)
            tokenClassification = "unknown";
        else
            tokenClassification = "unexpected";

        if (expToken != null)
        {
            if (useQuotesForExpToken)
                System.err.println("Parsing error at line "+t.lineNum()+": "+tokenClassification+" token \""+t.tokenVal()+"\". " +
                        "Expected \""+expToken+"\"");
            else
                System.err.println("Parsing error at line "+t.lineNum()+": "+tokenClassification+" token \""+t.tokenVal()+"\". " +
                        "Expected "+expToken);
        }
        else
            System.err.println("Parsing error at line "+t.lineNum()+": "+tokenClassification+" token \""+t.tokenVal()+"\"");

        t.close();
        System.exit(1);
    }

    // Prints an error message for an undeclared identifier and exits the program
    public static void throwUndecIdError (Tokenizer t, String id)
    {
        System.err.println("Parsing error at line "+t.lineNum()+": identifier \""+id+"\" has not been declared");
        t.close();
        System.exit(1);
    }

    // Prints an error message for a duplicated declared identifier and exits the program
    public static void throwExistIdError (Tokenizer t, String id)
    {
        System.err.println("Parsing error at line "+t.lineNum()+": identifier \""+id+"\" has already been declared");
        t.close();
        System.exit(1);
    }

    // Prints an error message for an uninitialized identifier and exits the program
    public static void throwUninitIdError (Tokenizer t, String id)
    {
        System.err.println("Parsing error at line "+t.lineNum()+": identifier \""+id+"\" has not been initialized");
        t.close();
        System.exit(1);
    }

    // Prints an error message consisting of only the string [errorMessage]
    public static void throwCustomError (Tokenizer t, String errorMessage)
    {
        System.err.println(errorMessage);
        t.close();
        System.exit(1);
    }

    // Writes [line] according to the pretty print's implementation
    public static void prettyPrintWrite (String line)
    {
        if (line != null)
            System.out.print(line);
    }

    // Creates an indentation for a new pretty print line
    public static void prettyPrintIndent ()
    {
        for (int i = 0; i < indentAmount; i++)
            System.out.print("\t");
    }

    // Increments the pretty print indentation formatting
    public static void prettyPrintIncreaseIndent ()
    {
        indentAmount++;
    }

    // Decrements the pretty print indentation formatting
    public static void prettyPrintDecreaseIndent ()
    {
        indentAmount--;
        if (indentAmount < 0)
            indentAmount = 0;
    }
}
