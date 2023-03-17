package parsetree.mathnodes;

import parsetree.AbstractParseTreeNode;
import parsetree.IMathNode;
import parsetree.Utils;

import java.util.HashMap;

public class Identifier extends AbstractParseTreeNode implements IMathNode
{
    private static final HashMap<String, Identifier> declaredIds = new HashMap<>();
    private static boolean declaringEnabled = true;

    private String name;
    private int value;
    private boolean initialized;

    public Identifier ()
    {
        super();

        this.name = null;
        value = 0;
        initialized = false;
    }

    public void parse ()
    {
        if (Utils.getToken(tokenizer.getToken()) != Utils.Token.IDENTIFIER)
            Utils.throwUnexpTokenError(tokenizer.tokenVal(), "identifier", false);

        name = tokenizer.idName();
        tokenizer.skipToken();

        // Check for any existing identifiers with the same name
        for (String idName : declaredIds.keySet())
        {
            if (idName.equals(name))
            {
                if (declaringEnabled)
                    Utils.throwUnexpTokenError(name, "identifier "+name+" has already been declared", false);
                else
                    return;
            }
        }

        // If an identifier is being used in the program, make sure it has been declared
        if (!declaringEnabled)
            Utils.throwUnexpTokenError(name, "identifier "+name+" has not been declared",
                    false);

        declaredIds.put(name, this);
    }

    public int eval ()
    {
        if (!declaredIds.get(name).initialized)
            Utils.throwUninitIdError(name);
        return declaredIds.get(name).value;
    }

    public void print()
    {
        Utils.prettyPrintWrite(name);
    }

    public static void disableDeclaring ()
    {
        declaringEnabled = false;
    }

    public void setValue (int newValue)
    {
        declaredIds.get(name).initialized = true;
        declaredIds.get(name).value = newValue;
    }

    public String getName ()
    {
        return name;
    }
}
