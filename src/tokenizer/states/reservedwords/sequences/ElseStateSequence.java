package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class ElseStateSequence extends AbstractStateSequence
{
    public ElseStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "lse");
    }
}
