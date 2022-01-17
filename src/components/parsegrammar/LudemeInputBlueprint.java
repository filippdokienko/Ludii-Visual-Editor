package components.parsegrammar;

import components.ludemeblock.grammar.input.Input;

public class LudemeInputBlueprint implements Input {
    private final String NAME;
    private final LudemeBlueprint REQUIRED_LUDEME;
    private boolean OPTIONAL = false;
    private boolean COLLECTION = false;

    public LudemeInputBlueprint(String name, LudemeBlueprint requiredLudeme){
        this.NAME = name;
        this.REQUIRED_LUDEME = requiredLudeme;
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
        return COLLECTION;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public LudemeBlueprint getRequiredLudeme(){
        return REQUIRED_LUDEME;
    }

    public void setOptional(boolean optional){
        OPTIONAL = optional;
    }

    public void setCollection(boolean collection){
        COLLECTION = collection;
    }
}
