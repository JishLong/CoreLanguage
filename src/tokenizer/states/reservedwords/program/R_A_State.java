package tokenizer.states.reservedwords.program;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;

public class R_A_State extends AbstractState
{
    public R_A_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'a')
            return new A_M_State(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
