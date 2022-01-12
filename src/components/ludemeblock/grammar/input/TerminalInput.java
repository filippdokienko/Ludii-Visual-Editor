package components.ludemeblock.grammar.input;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TerminalInput implements Input{

    private final String NAME;
    private final TerminalInputType TYPE;
    private final List<Terminal> TERMINALS; // only relevant for TerminalInputType = DROPDOWN
    private final boolean OPTIONAL;

    public TerminalInput(String name, TerminalInputType type){
        this.NAME = name;
        this.TYPE = type;
        this.TERMINALS =  new ArrayList<>();
        this.OPTIONAL = false;
    }

    public TerminalInput(String name, TerminalInputType type, boolean optional){
        this.NAME = name;
        this.TYPE = type;
        this.TERMINALS =  new ArrayList<>();
        this.OPTIONAL = optional;
    }

    public TerminalInput(String name, TerminalInputType type, List<Terminal> terminals){
        this.NAME = name;
        this.TYPE = type;
        this.TERMINALS = terminals;
        this.OPTIONAL = false;
    }

    public TerminalInput(String name, TerminalInputType type, List<Terminal> terminals, boolean optional){
        this.NAME = name;
        this.TYPE = type;
        this.TERMINALS = terminals;
        this.OPTIONAL = optional;
    }

    public JComponent getComponent(){
        switch(TYPE){
            case STRING:
                return new JTextField(); // Returns a textfield
            case INTEGER:
                return new JSpinner(new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1)); // returns a spinner TODO: whats minimum/maximum?
            case DROPDOWN:
                JComboBox<Terminal> comboBox = new JComboBox<>();
                for(Terminal t : TERMINALS)
                    comboBox.addItem(t);
                return comboBox;
        }
        return null;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public boolean isOptional() {
        return OPTIONAL;
    }

    @Override
    public boolean isChoice() {
        return false;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
