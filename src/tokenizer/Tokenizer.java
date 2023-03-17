package tokenizer;

import tokenizer.states.IState;
import tokenizer.states.starting.StartState;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// Converts a given file into a list of tokens in the Core language
public class Tokenizer
{
    private BufferedReader fileReader;

    // A list of token identifiers for the line currently being read
    private ArrayList<Integer> tokenIdentifiers;
    // A list of actual token values (i.e., "ABC123", "100", "while", "&&", etc.) for the line currently being read
    private ArrayList<String> tokenValues;
    // The index the tokenizer is set to for external users - aligns with both the token identifers and values
    private int index;

    public Tokenizer(String fileName)
    {
        try
        {
            fileReader = new BufferedReader(new FileReader(fileName));
            tokenIdentifiers = new ArrayList<>();
            tokenValues = new ArrayList<>();
            index = 0;

            // We want to read lines until we get one that actually contains tokens
            while (tokenIdentifiers.isEmpty())
                tokenizeLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Returns a token identifier between 1 and 34
    public int getToken ()
    {
        // If the file doesn't contain anything, we'll just say we're at the EOF
        int tokenIdentifier = 33;
        if (!tokenIdentifiers.isEmpty())
            tokenIdentifier = tokenIdentifiers.get(index);

        // If we have an error token, we'll print a helpful error message
        if (tokenIdentifier == 34)
        {
            System.out.println("Error: \""+tokenValues.get(index)+"\" is not a token");
        }

        return tokenIdentifier;
    }

    // Moves on to the next token if more remain and no errors have occurred
    public void skipToken()
    {
        int currentToken = 33;
        if (!tokenIdentifiers.isEmpty())
            currentToken = tokenIdentifiers.get(index);

        // Only move to the next token if we're at a valid token
        if (currentToken > 0 && currentToken < 33)
            index++;

        // If we've passed the line's last token, we'll move on
        if (index == tokenIdentifiers.size())
        {
            tokenIdentifiers.clear();
            tokenValues.clear();
            index = 0;

            // We want to read lines until we get one that actually contains tokens
            while (tokenIdentifiers.isEmpty())
                tokenizeLine();
        }
    }

    // If the current token is an integer, returns its integer value
    public int intVal ()
    {
        if (tokenIdentifiers.get(index) == 31)
            return Integer.parseInt(tokenValues.get(index));

        else
        {
            System.err.println("Error: call to intVal() cannot return integer value of non-integer token");
            System.exit(1);
        }

        // Just in case :)
        return -1;
    }

    // If the current token is an identifier, returns its identifier value
    public String idName ()
    {
        if (tokenIdentifiers.get(index) == 32)
            return tokenValues.get(index);
        else
        {
            System.err.println("Error: call to idName() cannot return identifier value of non-identifier token");
            System.exit(1);
        }

        // Just in case :)
        return "";
    }

    // Returns the string value of the current token
    public String tokenVal ()
    {
        if (tokenIdentifiers.get(index) != 33)
            return tokenValues.get(index);
        else
        {
            System.err.println("Error: call to tokenVal() cannot return value of EOF token");
            System.exit(1);
        }

        // Just in case :)
        return "";
    }

    // Converts the contents of one file line into a list of tokens in the Core language
    private void tokenizeLine ()
    {
        String line = "";
        String currentToken = "";

        // Try to read the line; if there aren't any more, we've reached the EOF
        try {
            if (fileReader.ready())
            {
                line = fileReader.readLine();
            }
            else
            {
                tokenIdentifiers.add(33);
                fileReader.close();
                return;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Create a new start state
        IState currentState = null;
        if (!line.isEmpty())
            currentState = new StartState('\n', line.charAt(0));

        for (int i = 0; i < line.length(); i++)
        {
            // Go to the next state
            if (i < line.length() - 1)
                currentState = currentState.nextState(line.charAt(i + 1));
            else
                currentState = currentState.nextState('\n');

            // Tokens don't have any white space in them
            if (!Utils.isWhitespace(line.charAt(i)))
                currentToken += line.charAt(i);

            // If we have a finished token, add it to the lists
            if (currentState.isTokenFinished())
            {
                tokenIdentifiers.add(Utils.getTokenIdentifier(currentToken));
                tokenValues.add(currentToken);
                currentToken = "";
            }
            // If we're in an error state OR we're at the end of a line with a non-token, we've reached an error
            else if (currentState.isErrorState() || (i == line.length() - 1 && !currentToken.isEmpty()))
            {
                tokenIdentifiers.add(34);
                tokenValues.add(currentToken);

                // We won't be reading any more of the file after this
                try
                {
                    fileReader.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                return;
            }

        }
    }
}
