package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.error.ErrorState;
import tokenizer.states.starting.StartState;

public class IdentifierAcceptingState extends AbstractState
{
    public IdentifierAcceptingState(char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar))
            return new StartState(prevChar, nextChar).nextState(secondNextChar);

        if (Utils.isUnrecognized(nextChar))
            return new ErrorState();

        return new IdentifierAcceptingState(nextChar, secondNextChar);
    }

    public boolean isTokenFinished()
    {
        return (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar));
    }

    public boolean isFinalState()
    {
        return true;
    }
}
