package tokenizer;

public class Utils
{
    // Returns whether [c] is a whitespace character that is recognized in the Core language
    public static boolean isWhitespace (char c)
    {
        return Character.isWhitespace(c);
    }

    // Returns whether [c] is a special symbol character that is recognized in the Core language
    public static boolean isSpecialSymbol (char c)
    {
        return c == ';' || c == ',' || c == '=' || c == '!' || c == '[' || c == ']' || c == '&' || c == '|' ||
                c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '<' || c == '>';
    }

    // Returns whether [c] is a digit that is recognized in the Core language
    public static boolean isDigit (char c)
    {
        return Character.isDigit(c);
    }

    // Returns whether [c] is an uppercase letter that is recognized in the Core language
    public static boolean isUppercaseLetter (char c)
    {
        return Character.isUpperCase(c);
    }

    // Returns whether [c] is a lowercase letter that is recognized in the Core language
    public static boolean isLowercaseLetter (char c)
    {
        boolean returnValue = Character.isLowerCase(c);

        // Some lowercase letters are never used in the Core language
        if (returnValue && c == 'c' || c == 'j' || c == 'k' || c == 'q' || c == 'u' || c == 'v' || c == 'x' ||
                c == 'y' || c == 'z')
            returnValue = false;

        return returnValue;
    }

    // Returns whether [c] is unrecognized in the Core language
    public static boolean isUnrecognizedChar (char c)
    {
        return !isDigit(c) && !isUppercaseLetter(c) && !isLowercaseLetter(c) && !isSpecialSymbol(c) && !isWhitespace(c);
    }

    // Returns the identifier of [token], given that [token] is a valid token in the Core language
    public static int getTokenIdentifier (String token)
    {
        // Check to see if the token is an identifier or a digit - we don't know its exact value
        if (isUppercaseLetter(token.charAt(0)))
            return 32;
        else if (isDigit(token.charAt(0)))
            return 31;

        // For other tokens, we do know their exact value, so we can just check for that
        switch (token)
        {
            case "program":
                return 1;
            case "begin":
                return 2;
            case "end":
                return 3;
            case "int":
                return 4;
            case "if":
                return 5;
            case "then":
                return 6;
            case "else":
                return 7;
            case "while":
                return 8;
            case "loop":
                return 9;
            case "read":
                return 10;
            case "write":
                return 11;
            case ";":
                return 12;
            case ",":
                return 13;
            case "=":
                return 14;
            case "!":
                return 15;
            case "[":
                return 16;
            case "]":
                return 17;
            case "&&":
                return 18;
            case "||":
                return 19;
            case "(":
                return 20;
            case ")":
                return 21;
            case "+":
                return 22;
            case "-":
                return 23;
            case "*":
                return 24;
            case "!=":
                return 25;
            case "==":
                return 26;
            case "<":
                return 27;
            case ">":
                return 28;
            case "<=":
                return 29;
            case ">=":
                return 30;
            default:
                // If we get to here, something has gone terribly wrong :(
                return -1;
        }
    }
}
