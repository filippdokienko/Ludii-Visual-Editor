package grammar;

import java.util.ArrayList;
import java.util.List;

public class Ludeme {
    public final String NAME;
    public List<Constructor> CONSTRUCTORS = new ArrayList<>();


    public Ludeme(String name, List<Constructor> constructors){
        this.NAME = name;
        this.CONSTRUCTORS = constructors;
    }

    public Ludeme(String name){
        this.NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public List<Constructor> getConstructors(){
        return CONSTRUCTORS;
    }
    public void addConstructor(Constructor c){
        CONSTRUCTORS.add(c);
    }
}
