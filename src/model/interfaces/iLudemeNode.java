package model.interfaces;

/**
 * Interface for a node representation of a ludeme in the current description
 * @author Filipp Dokienko
 */

import grammar.Constructor;
import grammar.Ludeme;
import grammar.input.Input;

import javax.swing.*;
import java.util.List;

public interface iLudemeNode {

    // Ludeme the node represents
    Ludeme getLudeme();

    // (User-) selected constructor for this Ludeme
    Constructor getCurrentConstructor();
    void setCurrentConstructor(Constructor selectedConstructor);

    // User-provided inputs (order by first to last required input)
    Object[] getProvidedInputs();
    void setProvidedInput(int index, Object providedInput);
    void setProvidedInput(Input input, Object providedInput);

    /* The .lud equivalent of that node
     e.g. for a (game ) for an empty <game> LudemeNode
     */
    String getStringRepresentation();


}
