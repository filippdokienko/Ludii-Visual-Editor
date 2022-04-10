package view.components.ludemenodecomponent.inputs;

import model.grammar.Constructor;
import model.grammar.Ludeme;
import model.grammar.input.ChoiceInput;
import model.grammar.input.Input;
import model.grammar.input.LudemeInput;
import view.components.ludemenodecomponent.LudemeNodeComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InputInformation {

    private final int INDEX; // index of input in constructor
    private final Constructor CONSTRUCTOR;
    private final Input INPUT;
    private final List<Ludeme> LUDEME_INPUTS;

    public InputInformation(Constructor constructor, Input input){
        this.CONSTRUCTOR = constructor;
        this.INPUT = input;
        this.INDEX = getIndex(constructor, input);
        this.LUDEME_INPUTS = getPossibleLudemeInputs(input);
    }

    private int getIndex(Constructor constructor, Input input){
        return constructor.getInputs().indexOf(input);
    }

    private List<Ludeme> getPossibleLudemeInputs(Input input){
        List<Ludeme> possibleLudemeInputs = new ArrayList<>();

        if(input.isChoice()){
            for(Input in : ((ChoiceInput)input).getInputs()){
                possibleLudemeInputs.add(((LudemeInput) in).getRequiredLudeme());
            }
            return possibleLudemeInputs;
        }
        if(input instanceof LudemeInput){
            possibleLudemeInputs.add(((LudemeInput) input).getRequiredLudeme());
            return possibleLudemeInputs;
        }

        return possibleLudemeInputs;
    }

    public List<Ludeme> getPossibleLudemeInputs(){
        return LUDEME_INPUTS;
    }

    public boolean isOptional(){
        return INPUT.isOptional();
    }

    public boolean isCollection(){
        return INPUT.isCollection();
    }

    public boolean isChoice(){
        return INPUT.isChoice();
    }

    public int getIndex(){
        return INDEX;
    }

    public Input getInput(){
        return INPUT;
    }

    public Constructor getConstructor(){
        return CONSTRUCTOR;
    }

    @Override
    public String toString(){
        return INPUT.toString();
    }

}
