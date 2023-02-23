package tokenizer.states.specialsymbols;

import tokenizer.states.AbstractStateSequence;

public class OrStateSequence extends AbstractStateSequence
{
    public OrStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "||");
    }
}
