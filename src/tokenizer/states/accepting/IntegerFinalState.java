package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.starting.StartState;

public class IntegerFinalState extends AbstractState
{
    public IntegerFinalState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isDigit(nextChar))
            return new IntegerFinalState(nextChar, secondNextChar);
        if (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar))
            return new StartState(prevChar, nextChar).nextState(secondNextChar);
        else
            return new ErrorState();
    }

    public boolean isFinalState()
    {
        return true;
    }

    @Override
    public boolean isTokenFinished()
    {
        if (Utils.isDigit(nextChar))
            return false;
        if (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar))
            return true;
        else
            return false;
    }
}
