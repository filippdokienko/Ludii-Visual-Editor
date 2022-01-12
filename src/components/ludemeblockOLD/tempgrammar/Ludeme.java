package components.ludemeblockOLD.tempgrammar;

import java.util.List;

public class Ludeme {

    /*

    ludeme name
    list of inputs:
        input: (name, type)
                      type: int, ludeme, string, ... ("String", "Int", "Ludeme")

     */

    public String name;
    public List<Input> inputs;

    public Ludeme(String name,  List<Input> inputs){
        this.name = name;
        this.inputs = inputs;
    }

    public String getName(){
        return name;
    }

    public List<Input> getInputs(){
        return inputs;
    }
}
