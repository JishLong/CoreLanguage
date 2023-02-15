package tokenizer.states;

public interface IState
{
    IState nextState (char secondNextChar);

    boolean isFinalState ();

    boolean isTokenFinished ();

    boolean isErrorState ();
}
