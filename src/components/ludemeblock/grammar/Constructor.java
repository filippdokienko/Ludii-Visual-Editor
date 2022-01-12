package components.ludemeblock.grammar;

import components.ludemeblock.grammar.input.Input;

import java.util.List;

public class Constructor {
    private final List<Input> INPUTS;

    public Constructor(List<Input> inputs){
        this.INPUTS = inputs;
    }

    public List<Input> getInputs(){
        return INPUTS;
    }
}
