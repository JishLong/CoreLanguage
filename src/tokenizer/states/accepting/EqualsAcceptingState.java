package tokenizer.states.accepting;

import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.starting.StartState;

public class EqualsAcceptingState extends AbstractState
{
    public EqualsAcceptingState(char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (nextChar == '=')
            return new DefaultAcceptingState(nextChar, secondNextChar);

        return new StartState(prevChar, nextChar).nextState(secondNextChar);
    }

    public boolean isTokenFinished()
    {
        return !(nextChar == '=');
    }

    public boolean isFinalState()
    {
        return true;
    }
}
