package tokenizer.states.reservedwords.program;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;

public class R_O_State extends AbstractState
{
    public R_O_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'o')
            return new O_G_State(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
