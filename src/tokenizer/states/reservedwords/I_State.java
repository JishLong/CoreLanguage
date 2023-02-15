package tokenizer.states.reservedwords;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.reservedwords.intWord.N_T_State;

public class I_State extends AbstractState
{
    public I_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'f')
            return new DefaultFinalState(nextChar, secondNextChar);
        else if (nextChar == 'n')
            return new N_T_State(nextChar, secondNextChar);
        else
            return new ErrorState();
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
