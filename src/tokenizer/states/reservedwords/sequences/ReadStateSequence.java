package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class ReadStateSequence extends AbstractStateSequence
{
    public ReadStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "read");
    }
}
