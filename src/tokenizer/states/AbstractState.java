package tokenizer.states;

import tokenizer.Utils;

public abstract class AbstractState implements IState
{
    protected final char prevChar, nextChar;

    protected AbstractState (char prevChar, char nextChar)
    {
        this.prevChar = prevChar;
        this.nextChar = nextChar;
    }

    public boolean isFinalState ()
    {
        return false;
    }

    public boolean isErrorState ()
    {
        return false;
    }
}
