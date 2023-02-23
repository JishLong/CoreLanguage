package tokenizer.states.reservedwords.startLetters;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.error.ErrorState;
import tokenizer.states.reservedwords.sequences.WhileStateSequence;
import tokenizer.states.reservedwords.sequences.WriteStateSequence;

public class WStartLetterState extends AbstractState
{
    public WStartLetterState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'r')
            return new WriteStateSequence(nextChar, secondNextChar);
        else if (nextChar == 'h')
            return new WhileStateSequence(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    public boolean isTokenFinished()
    {
        return false;
    }
}
