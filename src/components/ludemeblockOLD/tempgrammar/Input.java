package components.ludemeblockOLD.tempgrammar;

/**
 * Represents an Input for a Ludeme, consiting of an input name and input type.
 * If input type is a collection, it stores a list of possible values.
 * @author Filipp Dokienko
 */

import components.ludemeblockOLD.tempgrammar.inputtypes.InputType;

import java.util.ArrayList;
import java.util.List;

public class Input {

    private final String INPUT_NAME;
    private final InputType INPUT_TYPE;
    private final List COLLECTION_VALUES;

    /**
     * Constructor for non-collection input types
     * @param inputName Name of the input, can be empty
     * @param inputType Type of input
     */
    public Input(String inputName, InputType inputType){
        this.INPUT_NAME = inputName;
        this.INPUT_TYPE = inputType;
        this.COLLECTION_VALUES = new ArrayList();
        if(inputType.isCollection()){
            System.out.println("Please use constructor(String inputName, InputType inputType, List collectionValues) for inputType = collection");
        }
    }

    /**
     * Constructor for collection-type inputs
     * @param inputName Name of the input, can be empty
     * @param inputType Type of input
     * @param collectionValues List of possible values for collection
     */
    public Input(String inputName, InputType inputType, List collectionValues){
        this.INPUT_NAME = inputName;
        this.INPUT_TYPE = inputType;
        this.COLLECTION_VALUES = collectionValues;
    }

    /**
     *
     * @return InputType of input
     */
    public InputType getInputType(){
        return INPUT_TYPE;
    }

    /**
     *
     * @return Name of input
     */
    public String getName(){
        return INPUT_NAME;
    }

    /**
     *
     * @return List of values of collection
     */
    public List getCollectionValues(){
        return COLLECTION_VALUES;
    }

}
