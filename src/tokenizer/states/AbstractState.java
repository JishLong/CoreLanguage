package tokenizer.states;

public abstract class AbstractState implements IState
{
    /* [prevChar]: the char that caused the transition from the last state to this state
       [nextChar]: the char that comes after this state (the next state's [prevState] )
     */
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
