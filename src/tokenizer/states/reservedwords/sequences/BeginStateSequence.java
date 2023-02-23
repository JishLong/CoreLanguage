package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class BeginStateSequence extends AbstractStateSequence
{
    public BeginStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "begin");
    }
}
