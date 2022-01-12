package components.ludemeblock.grammar.input;

public interface Input {
    public boolean isCollection();
    public boolean isTerminal();
    public boolean isOptional();
    public boolean isChoice();
    public String getName();
}