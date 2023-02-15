package tokenizer.states.reservedwords.write;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;

public class R_I_State extends AbstractState
{
    public R_I_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'i')
            return new I_T_State(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
