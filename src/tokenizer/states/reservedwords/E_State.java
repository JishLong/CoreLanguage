package tokenizer.states.reservedwords;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.reservedwords.elseWord.L_S_State;

public class E_State extends AbstractState
{
    public E_State (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (nextChar == 'l')
            return new L_S_State(nextChar, secondNextChar);
        else if (nextChar == 'n')
            return new _D_State(nextChar, secondNextChar);
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
