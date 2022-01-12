package components.ludemeblockOLD.tempgrammar.inputtypes;

/**
 * Represents an InputType of an input of a ludeme.
 * Example: Ludeme, String, Integer, Collection
 * @author Filipp Dokienko
 */

import javax.swing.*;

public abstract class InputType {
    /**
     *
     * @return boolean whether input type is a collection
     */
    public abstract boolean isCollection();

    /**
     *
     * @return string representing the input type
     */
    public abstract InputTypesName getType();

    /**
     *
     * @return JComponent for the way the value of type 'InputType' is inputted
     */
    public abstract JComponent getJComponent();


    /**
     *
     * @return Whether otherInputType is equal to current InputType
     */
    public abstract boolean equals(InputType otherInputType);
}
