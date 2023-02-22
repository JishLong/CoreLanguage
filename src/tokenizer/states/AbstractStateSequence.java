package tokenizer.states;

import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.extra.ErrorState;

public abstract class AbstractStateSequence implements IState
{
    /* [prevChar]: the char that caused the transition from the last state to this state
       [nextChar]: the char that comes after this state (the next state's [prevState] )
     */
    protected char prevChar, nextChar;
    protected final String sequence;
    protected int index;

    protected AbstractStateSequence (char prevChar, char nextChar, String sequence)
    {
        this.prevChar = prevChar;
        this.nextChar = nextChar;
        this.sequence = sequence;

        index = 1;
    }

    public boolean isFinalState ()
    {
        return false;
    }

    public boolean isErrorState ()
    {
        return false;
    }

    public IState nextState (char secondNextChar)
    {
        if (index < sequence.length() - 1)
        {
            if (sequence.charAt(index - 1) == prevChar && sequence.charAt(index) == nextChar)
                return getNextStateInSequence(secondNextChar);
            else
                return new ErrorState();
        }
        else
        {
            return new DefaultFinalState(nextChar, secondNextChar);
        }
    }

    protected IState getNextStateInSequence (char secondNextChar)
    {
        prevChar = nextChar;
        nextChar = secondNextChar;
        index++;
        return this;
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
