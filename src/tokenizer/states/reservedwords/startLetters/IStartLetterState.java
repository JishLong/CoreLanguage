package tokenizer.states.reservedwords.startLetters;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultAcceptingState;
import tokenizer.states.error.ErrorState;
import tokenizer.states.reservedwords.sequences.IntStateSequence;

public class IStartLetterState extends AbstractState
{
    public IStartLetterState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'f')
            return new DefaultAcceptingState(nextChar, secondNextChar);
        else if (nextChar == 'n')
            return new IntStateSequence(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    public boolean isTokenFinished()
    {
        return false;
    }
}
