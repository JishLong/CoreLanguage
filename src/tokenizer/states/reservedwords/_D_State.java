package tokenizer.states.reservedwords;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.extra.ErrorState;

public class _D_State extends AbstractState
{
    public _D_State(char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'd')
            return new DefaultFinalState(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
