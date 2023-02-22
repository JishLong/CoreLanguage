package tokenizer.states.specialsymbols;

import tokenizer.states.AbstractStateSequence;

public class AndStateSequence extends AbstractStateSequence
{
    public AndStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "&&");
    }
}
