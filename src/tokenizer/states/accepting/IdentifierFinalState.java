package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.starting.StartState;

public class IdentifierFinalState extends AbstractState
{
    public IdentifierFinalState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isUnrecognized(nextChar) || Utils.isLowercaseLetter(nextChar))
            return new ErrorState();
        if (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar))
            return new StartState(prevChar, nextChar).nextState(secondNextChar);
        else
            return new IdentifierFinalState(nextChar, secondNextChar);
    }

    public boolean isFinalState()
    {
        return true;
    }

    @Override
    public boolean isTokenFinished()
    {
        if (Utils.isUnrecognized(nextChar) || Utils.isLowercaseLetter(nextChar))
            return false;
        if (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar))
            return true;
        else
            return false;
    }
}
