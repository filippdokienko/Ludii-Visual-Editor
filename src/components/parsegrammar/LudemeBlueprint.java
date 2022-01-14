package components.parsegrammar;

import components.ludemeblock.grammar.Constructor;

import java.util.ArrayList;
import java.util.List;

public class LudemeBlueprint {
    public final String NAME;
    private List<Constructor> constructors = new ArrayList<>();

    public LudemeBlueprint(String name){
        this.NAME = name;
    }

    public void addConstructor(Constructor c){
        constructors.add(c);
    }

}
