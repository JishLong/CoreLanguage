package tokenizer;

import tokenizer.states.IState;
import tokenizer.states.starting.StartState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Scanner
{
    private BufferedReader fileReader;
    private IState currentState;
    private ArrayList<Integer> tokenIdentifiers;
    private ArrayList<String> tokenValues;
    private int index;

    public Scanner(String fileName)
    {
        try
        {
            fileReader = new BufferedReader(new FileReader(fileName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        tokenIdentifiers = new ArrayList<>();
        tokenValues = new ArrayList<>();
        index = 0;

        tokenizeLine();
    }

    public int getToken ()
    {
        return tokenIdentifiers.get(index);
    }

    public void skipToken()
    {
        if (tokenIdentifiers.get(index) != 33 && tokenIdentifiers.get(index) != 34)
            index++;
        if (index == tokenIdentifiers.size())
        {
            tokenIdentifiers.clear();
            tokenValues.clear();
            index = 0;
            tokenizeLine();
        }
    }

    public int intVal ()
    {
        if (tokenIdentifiers.get(index) == 31)
            return Integer.parseInt(tokenValues.get(index));
        else
        {
            System.err.println("intVal(): error: cannot get integer value of non-integer token");
            System.exit(1);
        }
        return -1;
    }

    public String idName ()
    {
        if (tokenIdentifiers.get(index) == 32)
            return tokenValues.get(index);
        else
        {
            System.err.println("idName(): error: cannot get identifier value of non-identifier token");
            System.exit(1);
        }
        return "";
    }

    private void tokenizeLine ()
    {
        String line = "";
        String currentToken = "";

        try {
            if (fileReader.ready())
            {
                line = fileReader.readLine();
            }
            else
            {
                tokenIdentifiers.add(33);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (!line.isEmpty())
        {
            currentState = new StartState('\n', line.charAt(0));
        }

        for (int i = 0; i < line.length(); i++)
        {
            if (i < line.length() - 1)
                currentState = currentState.nextState(line.charAt(i + 1));
            else
                currentState = currentState.nextState('\n');

            if (currentState.isErrorState())
            {
                tokenIdentifiers.add(34);
                break;
            }
            else
            {
                if (!Utils.isWhitespace(line.charAt(i)))
                    currentToken += line.charAt(i);
                if (currentState.isTokenFinished())
                {
                    tokenValues.add(currentToken);
                    tokenIdentifiers.add(Utils.getTokenIdentifier(currentToken));
                    currentToken = "";
                }
            }
        }
    }
}
