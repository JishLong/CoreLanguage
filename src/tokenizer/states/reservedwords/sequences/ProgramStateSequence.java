package tokenizer.states.reservedwords.sequences;

import tokenizer.states.AbstractStateSequence;

public class ProgramStateSequence extends AbstractStateSequence
{
    public ProgramStateSequence (char prevChar, char nextChar)
    {
        super(prevChar, nextChar, "program");
    }
}
