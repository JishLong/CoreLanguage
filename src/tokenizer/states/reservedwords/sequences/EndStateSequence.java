package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class EndStateSequence extends AbstractStateSequence
{
    public EndStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "nd");
    }
}
