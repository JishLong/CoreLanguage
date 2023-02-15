package tokenizer.states.reservedwords.intWord;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.extra.ErrorState;

public class N_T_State extends AbstractState
{
    public N_T_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 't')
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
