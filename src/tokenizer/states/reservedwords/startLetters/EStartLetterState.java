package tokenizer.states.reservedwords.startLetters;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.reservedwords.sequences.ElseStateSequence;
import tokenizer.states.reservedwords.sequences.EndStateSequence;

public class EStartLetterState extends AbstractState
{
    public EStartLetterState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'l')
            return new ElseStateSequence(nextChar, secondNextChar);
        else if (nextChar == 'n')
            return new EndStateSequence(nextChar, secondNextChar);
        else {
            return new ErrorState();
        }
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
