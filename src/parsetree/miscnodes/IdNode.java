package parsetree.miscnodes;

import parsetree.AbstractParseTreeNode;
import parsetree.Utils;
import java.util.HashMap;

public class IdNode extends AbstractParseTreeNode
{
    private static final String nodeName = "identifier";
    // A hashmap to quickly obtain an identifier from its name
    private static final HashMap<String, IdNode> declaredIds = new HashMap<>();
    // Whether or not variables are allowed to be declared
    private static boolean declaringEnabled = true;

    private final String name;
    private int value;
    private boolean initialized;

    private IdNode (String name)
    {
        super();
        this.name = name;
        value = 0;
        initialized = false;
    }

    public static IdNode parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.IDENTIFIER)
            Utils.throwUnexpTokenError(tokenizer, nodeName, false);
        String name = tokenizer.idName();
        tokenizer.skipToken();

        // Check for an existing identifier with the same name
        if (declaredIds.containsKey(name))
        {
            if (declaringEnabled)
                Utils.throwExistIdError(tokenizer, name);
            else
                return declaredIds.get(name);
        }

        // If an identifier is being used in the program, make sure it has been declared
        if (!declaringEnabled)
            Utils.throwUndecIdError(tokenizer, name);

        // If an identifier hasn't been declared and we're allowed to declare, then declare a new identifier
        IdNode newId = new IdNode(name);
        declaredIds.put(name, newId);
        return newId;
    }

    public void print ()
    {
        // No error checking here since if an identifier is instantiated it must be parsed correctly

        Utils.prettyPrintWrite(name);
    }

    public int evaluate ()
    {
        // No error checking here since if an identifier is instantiated it must be parsed correctly

        if (!initialized)
            Utils.throwUninitIdError(tokenizer, name);
        return value;
    }

    public void setValue (int newValue)
    {
        initialized = true;
        value = newValue;
    }

    public String getName ()
    {
        return name;
    }

    public static void setDeclaringMode (boolean b)
    {
        declaringEnabled = b;
    }
}
