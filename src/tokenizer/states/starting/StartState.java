package tokenizer.states.starting;

import tokenizer.Utils;
import tokenizer.states.AbstractState;
import tokenizer.states.IState;
import tokenizer.states.accepting.*;
import tokenizer.states.error.ErrorState;
import tokenizer.states.reservedwords.sequences.*;
import tokenizer.states.reservedwords.startLetters.*;
import tokenizer.states.specialsymbols.*;

// The (only) starting state in the DFA representation
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

        if (Utils.isUppercaseLetter(nextChar))
            return new IdentifierAcceptingState(nextChar, secondNextChar);

        if (Utils.isDigit(nextChar))
            return new IntegerAcceptingState(nextChar, secondNextChar);

        if (nextChar == '!' || nextChar == '=' || nextChar == '<' || nextChar == '>')
            return new EqualsAcceptingState(nextChar, secondNextChar);

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
                    return new DefaultAcceptingState(nextChar, secondNextChar);
        }

        return new ErrorState();
    }

    public boolean isTokenFinished()
    {
        return false;
    }
}
