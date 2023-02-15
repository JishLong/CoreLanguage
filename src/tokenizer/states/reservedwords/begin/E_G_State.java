package tokenizer.states.reservedwords.begin;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;

public class E_G_State extends AbstractState
{
    public E_G_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'g')
            return new G_I_State(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
