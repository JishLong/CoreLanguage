package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class WhileStateSequence extends AbstractStateSequence
{
    public WhileStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "hile");
    }
}
