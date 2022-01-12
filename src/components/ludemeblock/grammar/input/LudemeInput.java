package components.ludemeblock.grammar.input;

import components.ludemeblock.grammar.Ludeme;

public class LudemeInput implements Input{

    private final String NAME;
    private final Ludeme REQUIRED_LUDEME;
    private final boolean OPTIONAL;

    public LudemeInput(String name, Ludeme requiredLudeme){
        this.NAME = name;
        this.REQUIRED_LUDEME = requiredLudeme;
        this.OPTIONAL = false;
    }

    public LudemeInput(String name, Ludeme requiredLudeme, boolean optional){
        this.NAME = name;
        this.REQUIRED_LUDEME = requiredLudeme;
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
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Ludeme getRequiredLudeme(){
        return REQUIRED_LUDEME;
    }
}
