package tokenizer.states.starting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.accepting.EqualsFinalState;
import tokenizer.states.accepting.IdentifierFinalState;
import tokenizer.states.accepting.IntegerFinalState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.reservedwords.E_State;
import tokenizer.states.reservedwords.I_State;
import tokenizer.states.reservedwords.W_State;
import tokenizer.states.reservedwords.begin.B_E_State;
import tokenizer.states.reservedwords.loop.L_O_State;
import tokenizer.states.reservedwords.program.P_R_State;
import tokenizer.states.reservedwords.read.R_E_State;
import tokenizer.states.reservedwords.then.T_H_State;
import tokenizer.states.specialsymbols.And_Final_State;
import tokenizer.states.specialsymbols.Or_Final_State;

public class StartState extends AbstractState
{
    public StartState (char prevChar, char nextChar)
    {
        super(prevChar, nextChar);
    }

    public IState nextState (char secondNextChar)
    {
        if (Utils.isWhitespace(nextChar))
            return new StartState(nextChar, secondNextChar);

        else if (Utils.isUppercaseLetter(nextChar))
            return new IdentifierFinalState(nextChar, secondNextChar);
        else if (Utils.isDigit(nextChar))
            return new IntegerFinalState(nextChar, secondNextChar);
        else if (nextChar == '!' || nextChar == '=' || nextChar == '<' || nextChar == '>')
            return new EqualsFinalState(nextChar, secondNextChar);

        switch (nextChar)
        {
            case 'p':
                return new P_R_State(nextChar, secondNextChar);
            case 'b':
                return new B_E_State(nextChar, secondNextChar);
            case 't':
                return new T_H_State(nextChar, secondNextChar);
            case 'l':
                return new L_O_State(nextChar, secondNextChar);
            case 'r':
                return new R_E_State(nextChar, secondNextChar);
            case 'e':
                return new E_State(nextChar, secondNextChar);
            case 'w':
                return new W_State(nextChar, secondNextChar);
            case 'i':
                return new I_State(nextChar, secondNextChar);
            case '&':
                return new And_Final_State(nextChar, secondNextChar);
            case '|':
                return new Or_Final_State(nextChar, secondNextChar);
            default:
                if (Utils.isSpecialSymbol(nextChar))
                    return new DefaultFinalState(nextChar, secondNextChar);
                else
                    return new ErrorState();
        }
    }

    @Override
    public boolean isTokenFinished()
    {
        return false;
    }
}
