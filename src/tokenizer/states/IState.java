package tokenizer.states;

// A representation of a DFA state (or in some cases, a single sequence of states)
public interface IState
{
    // Returns the next state given the char coming after the next state is [secondNextChar]
    IState nextState (char secondNextChar);

    // Returns whether the token currently being built is finished
    boolean isTokenFinished ();

    // Returns whether this state is a final/accepting state
    boolean isFinalState ();

    // Returns whether this is an error state
    boolean isErrorState ();
}
