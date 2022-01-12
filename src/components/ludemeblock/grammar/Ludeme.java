package components.ludemeblock.grammar;

import java.util.List;

public class Ludeme {
    private final String NAME;
    private final List<Constructor> CONSTRUCTORS;


    public Ludeme(String name, List<Constructor> constructors){
        this.NAME = name;
        this.CONSTRUCTORS = constructors;
    }

    public String getName() {
        return NAME;
    }

    public List<Constructor> getConstructors(){
        return CONSTRUCTORS;
    }
}
