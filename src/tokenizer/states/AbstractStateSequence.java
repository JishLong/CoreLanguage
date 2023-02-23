package tokenizer.states;

import tokenizer.states.accepting.DefaultAcceptingState;
import tokenizer.states.error.ErrorState;

// A representation of a sequence of DFA states (essentially, a straight line
public abstract class AbstractStateSequence implements IState
{
    /* [prevChar]: the char that caused the transition from the last state to this state
       [nextChar]: the char that comes after this state (the next state's [prevState] )
     */
    private char prevChar, nextChar;
    // The string this DFA sequence represents
    private final String sequence;
    // The character in [sequence] that nextChar should ideally be
    private int index;

    protected AbstractStateSequence (char prevChar, char nextChar, String sequence)
    {
        this.prevChar = prevChar;
        this.nextChar = nextChar;
        this.sequence = sequence;

        index = 1;
    }

    public IState nextState (char secondNextChar)
    {
        if (index < sequence.length() - 1)
        {
            if (sequence.charAt(index - 1) == prevChar && sequence.charAt(index) == nextChar)
            {
                prevChar = nextChar;
                nextChar = secondNextChar;
                index++;

                return this;
            }
            else
                return new ErrorState();
        }
        else
        {
            return new DefaultAcceptingState(nextChar, secondNextChar);
        }
    }

    public boolean isTokenFinished()
    {
        return false;
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
