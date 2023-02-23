package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.error.ErrorState;
import tokenizer.states.starting.StartState;

public class DefaultAcceptingState extends AbstractState
{
    public DefaultAcceptingState(char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isLowercaseLetter(prevChar) && Utils.isDigit(nextChar))
            return new ErrorState();

        if (Utils.isLowercaseLetter(prevChar) && Utils.isUppercaseLetter(nextChar))
            return new ErrorState();

        if (Utils.isLowercaseLetter(prevChar) && Utils.isLowercaseLetter(nextChar))
            return new ErrorState();

        if (Utils.isLowercaseLetter(prevChar) && Utils.isUnrecognized(nextChar))
            return new ErrorState();

        return new StartState(prevChar, nextChar).nextState(secondNextChar);
    }

    public boolean isTokenFinished()
    {
        if (Utils.isLowercaseLetter(prevChar) && Utils.isDigit(nextChar))
            return false;

        if (Utils.isLowercaseLetter(prevChar) && Utils.isUppercaseLetter(nextChar))
            return false;

        if (Utils.isLowercaseLetter(prevChar) && Utils.isLowercaseLetter(nextChar))
            return false;

        if (Utils.isLowercaseLetter(prevChar) && Utils.isUnrecognized(nextChar))
            return false;

        return true;
    }

    public boolean isFinalState()
    {
        return true;
    }
}
