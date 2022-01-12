package components.ludemeblockOLD.tempgrammar.inputtypes;

/**
 * Represents the String InputType
 * @author Filipp Dokienko
 */

import javax.swing.*;

public class InputTypeString extends InputType{

    @Override
    public InputTypesName getType() {
        return InputTypesName.STRING;
    }

    @Override
    public JComponent getJComponent() {
        return new JTextField(); // Returns a textfield
    }

    @Override
    public boolean equals(InputType otherInputType) {
        return (otherInputType.getType() == getType());
    }

    @Override
    public boolean isCollection() {
        return false;
    }
}
