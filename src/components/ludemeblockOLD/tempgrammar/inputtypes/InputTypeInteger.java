package components.ludemeblockOLD.tempgrammar.inputtypes;

/**
 * Represents the Integer InputType
 * @author Filipp Dokienko
 */

import javax.swing.*;

public class InputTypeInteger extends InputType{

    @Override
    public InputTypesName getType() {
        return InputTypesName.INTEGER;
    }

    @Override
    public JComponent getJComponent() {
        return new JSpinner(new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1)); // returns a spinner
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
