package components.ludemeblock.grammar.input;

import java.util.List;

public class ChoiceInput implements Input {
    private final String NAME;
    private final List<Input> INPUTS;
    private final boolean OPTIONAL;

    public ChoiceInput(String name, List<Input> inputs){
        this.NAME = name;
        this.INPUTS = inputs;
        this.OPTIONAL = false;
    }

    public ChoiceInput(String name, List<Input> inputs, boolean optional){
        this.NAME = name;
        this.INPUTS = inputs;
        this.OPTIONAL = optional;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public boolean isOptional() {
        return OPTIONAL;
    }

    @Override
    public boolean isChoice() {
        return true;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
