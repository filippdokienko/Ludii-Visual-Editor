package components.parsegrammar;

import components.ludemeblock.grammar.Constructor;

import java.util.ArrayList;
import java.util.List;

public class LudemeBlueprint {
    public final String NAME;
    public List<Constructor> constructors = new ArrayList<>();

    public LudemeBlueprint(String name){
        this.NAME = name;
    }
    public LudemeBlueprint(String name, List<Constructor> c){
        this.NAME = name;
        this.constructors = c;
    }

    public void addConstructor(Constructor c){
        constructors.add(c);
    }

    public String getName(){ return NAME; }

}
