package tokenizer.states.accepting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.error.ErrorState;
import tokenizer.states.starting.StartState;

public class IntegerAcceptingState extends AbstractState
{
    public IntegerAcceptingState(char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState(char secondNextChar)
    {
        if (Utils.isDigit(nextChar))
            return new IntegerAcceptingState(nextChar, secondNextChar);

        if (Utils.isSpecialSymbol(nextChar) || Utils.isWhitespace(nextChar))
            return new StartState(prevChar, nextChar).nextState(secondNextChar);

        return new ErrorState();
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
