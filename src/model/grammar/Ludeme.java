package model.grammar;

import model.grammar.input.Input;
import model.grammar.input.LudemeInput;

import java.util.ArrayList;
import java.util.List;

public class Ludeme {
    public final String NAME;
    public boolean HIDDEN = true; // TODO
    public List<Constructor> CONSTRUCTORS = new ArrayList<>();


    public Ludeme(String name, List<Constructor> constructors){
        this.NAME = name;
        this.CONSTRUCTORS = constructors;

        // check whether hidden
        for(Constructor c : getConstructors()){
            if(HIDDEN == false) break;
            if(c.getInputs().size() > 1){
                this.HIDDEN = false;
                break;
            }
            for(Input in : c.getInputs()){
                if(!(in instanceof LudemeInput)){
                    this.HIDDEN = false;
                    break;
                }
            }
        }

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
        if(c.getInputs().size() > 1) HIDDEN = false;
        else {
            for(Input in : c.getInputs()){
                if(!(in instanceof LudemeInput) || in.isChoice() || in.isCollection()){
                    HIDDEN = false;
                    break;
                }
            }
        }
    }

    @Override
    public String toString(){
        return NAME;
    }
}
