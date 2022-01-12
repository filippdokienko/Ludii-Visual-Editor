package components.ludemeblockOLD.tempgrammar.inputtypes;

/**
 * Represents the Collection InputType
 * @author Filipp Dokienko
 */

import javax.swing.*;


public class InputTypeCollection extends InputType{

    @Override
    public InputTypesName getType() {
        return InputTypesName.COLLECTION;
    }

    @Override
    public JComponent getJComponent() {
        return new JComboBox<>(); // returns a drop down menu
    }

    @Override
    public boolean equals(InputType otherInputType) {
        return (otherInputType.getType() == getType());
    }

    @Override
    public boolean isCollection() {
        return true;
    }
}
