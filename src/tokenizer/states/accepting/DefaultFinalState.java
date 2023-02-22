package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.starting.StartState;

public class DefaultFinalState extends AbstractState
{
    public DefaultFinalState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isUnrecognized(nextChar))
            return new ErrorState();
        else if (Utils.isLowercaseLetter(prevChar) && Utils.isDigit(nextChar))
            return new ErrorState();
        else if (Utils.isLowercaseLetter(prevChar) && Utils.isUppercaseLetter(nextChar))
            return new ErrorState();
        else if (Utils.isLowercaseLetter(prevChar) && Utils.isLowercaseLetter(nextChar))
            return new ErrorState();
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
        else if (Utils.isLowercaseLetter(prevChar) && Utils.isDigit(nextChar))
            return false;
        else if (Utils.isLowercaseLetter(prevChar) && Utils.isUppercaseLetter(nextChar))
            return false;
        else if (Utils.isLowercaseLetter(prevChar) && Utils.isLowercaseLetter(nextChar))
            return false;
        else
            return true;
    }
}
