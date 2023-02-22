package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class WriteStateSequence extends AbstractStateSequence
{
    public WriteStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "rite");
    }
}
