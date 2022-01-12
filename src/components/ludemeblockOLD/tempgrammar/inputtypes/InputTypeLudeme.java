package components.ludemeblockOLD.tempgrammar.inputtypes;

/**
 * Represents the Ludeme InputType
 * @author Filipp Dokienko
 */

import javax.swing.*;

public class InputTypeLudeme extends InputType{
    @Override
    public InputTypesName getType() {
        return InputTypesName.LUDEME;
    }

    @Override
    public JComponent getJComponent() {
        return null; // No input field for Ludemes
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
