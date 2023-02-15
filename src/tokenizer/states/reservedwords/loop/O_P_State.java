package tokenizer.states.reservedwords.loop;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.extra.ErrorState;

public class O_P_State extends AbstractState
{
    public O_P_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'p')
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
