package tokenizer.states.error;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;

public class ErrorState extends AbstractState
{
    public ErrorState ()
    {
        super('\0', '\0');
    }

    public IState nextState(char secondNextChar)
    {
        return this;
    }

    public boolean isTokenFinished()
    {
        return false;
    }

    public boolean isErrorState ()
    {
        return true;
    }
}
