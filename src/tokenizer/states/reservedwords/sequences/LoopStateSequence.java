package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class LoopStateSequence extends AbstractStateSequence
{
    public LoopStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "loop");
    }
}
