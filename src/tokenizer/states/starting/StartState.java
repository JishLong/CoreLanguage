package tokenizer.states.starting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.DefaultFinalState;
import tokenizer.states.accepting.EqualsFinalState;
import tokenizer.states.accepting.IdentifierFinalState;
import tokenizer.states.accepting.IntegerFinalState;
import tokenizer.states.extra.ErrorState;
import tokenizer.states.reservedwords.sequences.*;
import tokenizer.states.reservedwords.startLetters.EStartLetterState;
import tokenizer.states.reservedwords.startLetters.IStartLetterState;
import tokenizer.states.reservedwords.startLetters.WStartLetterState;
import tokenizer.states.specialsymbols.AndStateSequence;
import tokenizer.states.specialsymbols.OrStateSequence;

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
                return new ProgramStateSequence(nextChar, secondNextChar);
            case 'b':
                return new BeginStateSequence(nextChar, secondNextChar);
            case 't':
                return new ThenStateSequence(nextChar, secondNextChar);
            case 'l':
                return new LoopStateSequence(nextChar, secondNextChar);
            case 'r':
                return new ReadStateSequence(nextChar, secondNextChar);
            case 'e':
                return new EStartLetterState(nextChar, secondNextChar);
            case 'w':
                return new WStartLetterState(nextChar, secondNextChar);
            case 'i':
                return new IStartLetterState(nextChar, secondNextChar);
            case '&':
                return new AndStateSequence(nextChar, secondNextChar);
            case '|':
                return new OrStateSequence(nextChar, secondNextChar);
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
