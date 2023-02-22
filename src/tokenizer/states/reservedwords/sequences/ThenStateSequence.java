package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class ThenStateSequence extends AbstractStateSequence
{
    public ThenStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "then");
    }
}
