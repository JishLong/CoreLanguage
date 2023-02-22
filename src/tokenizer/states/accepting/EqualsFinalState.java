package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.starting.StartState;

public class EqualsFinalState extends AbstractState
{
    public EqualsFinalState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isUnrecognized(nextChar))
            return new ErrorState();
        else if (nextChar == '=')
            return new DefaultFinalState(nextChar, secondNextChar);
        else
            return new StartState(prevChar, nextChar).nextState(secondNextChar);
    }

    public boolean isFinalState()
    {
        return true;
    }

    public boolean isTokenFinished()
    {
        if (Utils.isUnrecognized(nextChar))
            return false;
        else if (nextChar == '=')
            return false;
        else
            return true;
    }
}
