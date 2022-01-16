package components.ludemeblock.grammar.input;

import java.util.List;

// TODO: either remove, or finish

public class CollectionInput implements Input{

    private final String NAME;
    private final List<Input> INPUTS;
    private final boolean OPTIONAL;

    public CollectionInput(String name, List<Input> inputs){
        this.NAME = name;
        this.INPUTS = inputs;
        this.OPTIONAL = false;
    }

    public CollectionInput(String name, List<Input> inputs, boolean optional){
        this.NAME = name;
        this.INPUTS = inputs;
        this.OPTIONAL = optional;
    }


    @Override
    public boolean isCollection() {
        return true;
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

    @Override
    public void setOptional(boolean optional) {

    }

    @Override
    public void setCollection(boolean collection) {

    }

    public List<Input> getInputs(){
        return INPUTS;
    }
}
