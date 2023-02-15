package tokenizer.states.reservedwords;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.reservedwords.whileWord.H_I_State;
import tokenizer.states.reservedwords.write.R_I_State;

public class W_State extends AbstractState
{
    public W_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'r')
            return new R_I_State(nextChar, secondNextChar);
        else if (nextChar == 'h')
            return new H_I_State(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
