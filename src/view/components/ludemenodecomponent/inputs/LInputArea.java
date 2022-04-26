package view.components.ludemenodecomponent.inputs;

import model.LudemeNode;
import model.grammar.Constructor;
import model.grammar.Ludeme;
import model.grammar.input.ChoiceInput;
import model.grammar.input.Input;
import model.grammar.input.LudemeInput;
import view.components.DesignPalette;
import view.components.ludemenodecomponent.LudemeNodeComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LInputArea extends JPanel {

    private List<LInputField> inputFields = new ArrayList<>();
    ;
    private LudemeNodeComponent LNC;

    private List<Constructor> activeConstructors = new ArrayList<>();
    private List<Constructor> inactiveConstructors = new ArrayList<>();

    private List<InputInformation> allInputInformations = new ArrayList<>();
    private List<InputInformation> filledInputInformations = new ArrayList<>();

    private static final boolean DEBUG = true;


    public LInputArea(LudemeNodeComponent ludemeNodeComponent) {
        this.LNC = ludemeNodeComponent;

        activeConstructors = new ArrayList<>(LNC.getLudemeNode().getLudeme().getConstructors());

        inputFields = getInputFields(LNC);
        drawInputFields();

    }

    private List<LInputField> getInputFields(LudemeNodeComponent ludemeNodeComponent) {
        List<LInputField> fields = new ArrayList<>();

        if (ludemeNodeComponent.dynamic && activeConstructors.size() > 1) {
            List<InputInformation> inputInformationList = new ArrayList<>();
            for (Constructor c : ludemeNodeComponent.getLudemeNode().getLudeme().getConstructors()) {
                for (Input input : c.getInputs()) {
                    InputInformation ii = new InputInformation(c, input);
                    inputInformationList.add(ii);
                    allInputInformations.add(ii);
                }
            }
            fields.add(new LInputField(ludemeNodeComponent, inputInformationList));
            return fields;
        }

        List<Input> inputs = ludemeNodeComponent.getLudemeNode().getCurrentConstructor().getInputs();

        List<InputInformation> consequentOptionalInputs = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            Input in = inputs.get(i);
            InputInformation ii = new InputInformation(ludemeNodeComponent.getLudemeNode().getCurrentConstructor(), in);

            if (ii.isOptional() && LNC.getLudemeNode().getProvidedInputs()[ii.getIndex()] == null) {
                consequentOptionalInputs.add(ii);
                continue;
            } else if (consequentOptionalInputs.size() == 1) {
                LInputField inputFieldPrevious = new LInputField(ludemeNodeComponent, consequentOptionalInputs.get(0));
                fields.add(inputFieldPrevious);
                consequentOptionalInputs = new ArrayList<>();
            } else if (consequentOptionalInputs.size() > 1) {
                LInputField inputFieldPrevious = new LInputField(ludemeNodeComponent, consequentOptionalInputs);
                fields.add(inputFieldPrevious);
                consequentOptionalInputs = new ArrayList<>();
            }

            LInputField inputField = new LInputField(ludemeNodeComponent, ii);
            fields.add(inputField);
        }
        if (consequentOptionalInputs.size() == 1) {
            LInputField inputFieldPrevious = new LInputField(ludemeNodeComponent, consequentOptionalInputs.get(0));
            fields.add(inputFieldPrevious);
            consequentOptionalInputs = new ArrayList<>();
        }
        if (consequentOptionalInputs.size() > 1) {
            LInputField inputFieldPrevious = new LInputField(ludemeNodeComponent, consequentOptionalInputs);
            fields.add(inputFieldPrevious);
            consequentOptionalInputs = new ArrayList<>();
        }
        return fields;
    }

    public void drawInputFields() {
        removeAll();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBackground(DesignPalette.BACKGROUND_LUDEME_BODY);

        for (LInputField inputField : inputFields) {
            inputField.setAlignmentX(LEFT_ALIGNMENT);
            add(inputField);
        }

        int preferredHeight = getPreferredSize().height;
        setSize(new Dimension(LNC.getWidth(), preferredHeight));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        LNC.updateComponent();
        LNC.updatePositions();


        revalidate();
        repaint();
        setVisible(true);
    }

    public void updateComponent(LudemeNode node, LInputField c_inputField, boolean removed) {

        if (LNC.dynamic && !removed) {
            Ludeme ludeme = node.getLudeme();
            // find LInputField containg the node's ludeme
            LInputField lif0 = c_inputField;
            for (LInputField inputField : inputFields) {
                for (InputInformation ii : inputField.getInputInformations()) {
                    if (ii.getPossibleLudemeInputs().contains(ludeme)) {
                        lif0 = inputField;
                        break;
                    }
                    if (lif0 != null) break;
                }
            }
            if (DEBUG) System.out.println("[DYNAMIC LIA]: lif0 = " + lif0);

            // find all InputInformation containing the node's ludeme
            List<InputInformation> IC = new ArrayList<>();
            for (InputInformation ii : lif0.getInputInformations()) {
                if (ii.getPossibleLudemeInputs().contains(ludeme)) {
                    IC.add(ii);
                }
            }
            if (DEBUG) System.out.println("[DYNAMIC LIA]: IC = " + IC);

            // active constructors are all ii's constructors in IC
            List<Constructor> newActiveC = new ArrayList<>();
            List<Constructor> newInactiveC = new ArrayList<>();
            for (InputInformation ii : IC) {
                if (!newActiveC.contains(ii.getConstructor())) newActiveC.add(ii.getConstructor());
            }
            // every constructor in activeConsturctors but not in newActiveC is inactive
            for (Constructor c : activeConstructors) {
                if (!newActiveC.contains(c)) newInactiveC.add(c);
            }

            // remove

            activeConstructors = newActiveC;
            inactiveConstructors = newInactiveC;

            if (DEBUG) System.out.println("[DYNAMIC LIA]: newActiveC = " + newActiveC.size() + ", " + newActiveC);
            if (DEBUG) System.out.println("[DYNAMIC LIA]: newInactiveC = " + newInactiveC.size() + ", " + newInactiveC);

            // get all InputIndices of every ii in IC
            List<Integer> IX = new ArrayList<>();
            for (InputInformation ii : IC) {
                IX.add(ii.getIndex());
            }
            if (DEBUG) System.out.println("[DYNAMIC LIA]: IX = " + IX);

            // if |IX| > 1, then split up lif0 in lif0,1 and lif0,2
            if (IX.size() > 1) {
                List<InputInformation> lif01_iis = new ArrayList<>();
                List<InputInformation> lif02_iis = new ArrayList<>();

                // for every InputInformation ii in IC:
                //          for every Inputinformation j in allInputInformations:
                //                  if constructor(j) == constructor(i) && index(j) < index(ii) -> lif01_iis.add(j)
                //                  if constructor(j) == constructor(i) && index(j) > index(ii) -> lif02_iis.add(j)
                for (InputInformation ii : IC) {
                    for (InputInformation j : allInputInformations) {
                        if (j.getConstructor() == ii.getConstructor() && j.getIndex() < ii.getIndex()) {
                            lif01_iis.add(j);
                        }
                        if (j.getConstructor() == ii.getConstructor() && j.getIndex() > ii.getIndex()) {
                            lif02_iis.add(j);
                        }
                    }
                }
                if (DEBUG) System.out.println("[DYNAMIC LIA]: lif01_iis = " + lif01_iis);
                if (DEBUG) System.out.println("[DYNAMIC LIA]: lif02_iis = " + lif02_iis);

                // TODO:

                // if |lif0,1| == 0 ->
                //      replace lif0 with lif0,2
                //      add setToSingle(ludeme) ABOVE lif0

                // if |lif0,2| == 0 ->
                //      replace lif0 with lif0,1
                //      add setToSingle(ludeme) BELOW lif0

                // else ->
                //      replace lif0 with lif0,1
                //      add setToSingle(ludeme) BELOW lif0
                //      add lif0,2 BELOW setToSingle(ludeme)
            } else {
                // TODO: Only one activeConstructor in such case
                inputFields = getInputFields(LNC);
                drawInputFields();
                return;
            }
        }

        if (LNC.dynamic) {
            List<Constructor> matches = new ArrayList<>();
            Ludeme l = node.getLudeme();

            if (removed) {

                InputInformation removedInputInformation = null;
                for (LInputField lif : inputFields) {
                    if (lif.isSingle() && lif.getInputInformation().getInput() instanceof LudemeInput && ((LudemeInput) lif.getInputInformation().getInput()).getRequiredLudeme() == l) {
                        removedInputInformation = lif.getInputInformation();
                        break;
                    }
                }
                filledInputInformations.remove(removedInputInformation);


                for (Constructor c : inactiveConstructors) {
                    for (Input i : c.getInputs()) {
                        if (i instanceof LudemeInput) {
                            LudemeInput li = (LudemeInput) i;
                            if (li.getRequiredLudeme().equals(l)) {
                                if (!matches.contains(c)) matches.add(c);
                            }
                        } else if (i instanceof ChoiceInput) {
                            ChoiceInput ci = (ChoiceInput) i;
                            for (Input i_c : ci.getInputs()) {
                                if (i_c instanceof LudemeInput) {
                                    LudemeInput li = (LudemeInput) i_c;
                                    if (li.getRequiredLudeme().equals(l)) {
                                        if (!matches.contains(c)) matches.add(c);
                                    }
                                }
                            }
                        }
                    }
                }
                activeConstructors.addAll(matches);
                inactiveConstructors.removeAll(matches);
            } else {


                for (Constructor c : activeConstructors) {
                    boolean constructorContainsLudeme = false;
                    for (Input i : c.getInputs()) {
                        if (i instanceof LudemeInput) {
                            LudemeInput li = (LudemeInput) i;
                            if (li.getRequiredLudeme().equals(l)) {
                                constructorContainsLudeme = true;
                            }
                        } else if (i instanceof ChoiceInput) {
                            ChoiceInput ci = (ChoiceInput) i;
                            for (Input i_c : ci.getInputs()) {
                                if (i_c instanceof LudemeInput) {
                                    LudemeInput li = (LudemeInput) i_c;
                                    if (li.getRequiredLudeme().equals(l)) {
                                        constructorContainsLudeme = true;
                                    }
                                }
                            }
                        }
                    }
                    if (!constructorContainsLudeme) {
                        System.out.println("Does not contain ludeme " + l.getName() + ": " + c);
                        matches.add(c);
                    }
                }
                activeConstructors.removeAll(matches);
                inactiveConstructors.addAll(matches);
            }


            System.out.println("Active Constructors: " + activeConstructors.size() + ", " + activeConstructors);
            System.out.println("Inactive Constructors: " + inactiveConstructors.size() + ", " + inactiveConstructors);

            updateDynamic();

        } else {
            mergeOptionalArgumentsInOne();
        }
        if (!removed && c_inputField != null && !c_inputField.isSingle()) {
            c_inputField.setToSingle(node.getLudeme());
        }
        drawInputFields();
    }

    public LInputField addedConnection(LudemeNode node, LInputField c_inputField){

        if(LNC.dynamic){
            return addedConnectionDynamic(node, c_inputField);
        }

        // else if not dynamic:
        LInputField addedInputField = null;
        if(c_inputField != null && !c_inputField.isSingle()){
            addedInputField = c_inputField.setToSingle(node.getLudeme());
        }
        return addedInputField;
    }

    private LInputField addedConnectionDynamic(LudemeNode node, LInputField c_inputField) {
        Ludeme ludeme = node.getLudeme();
        // find LInputField containg the node's ludeme
        LInputField lif0 = c_inputField;
        for (LInputField inputField : inputFields) {
            for (InputInformation ii : inputField.getInputInformations()) {
                if (ii.getPossibleLudemeInputs().contains(ludeme)) {
                    lif0 = inputField;
                    break;
                }
                if (lif0 != null) break;
            }
        }
        if (DEBUG) System.out.println("[DYNAMIC LIA]: lif0 = " + lif0);

        // find all InputInformation containing the node's ludeme
        List<InputInformation> IC = new ArrayList<>();
        for (InputInformation ii : lif0.getInputInformations()) {
            if (ii.getPossibleLudemeInputs().contains(ludeme)) {
                IC.add(ii);
            }
        }
        if (DEBUG) System.out.println("[DYNAMIC LIA]: IC = " + IC);

        // active constructors are all ii's constructors in IC
        List<Constructor> newActiveC = new ArrayList<>();
        List<Constructor> newInactiveC = new ArrayList<>();
        for (InputInformation ii : IC) {
            if (!newActiveC.contains(ii.getConstructor())) newActiveC.add(ii.getConstructor());
        }
        // every constructor in activeConsturctors but not in newActiveC is inactive
        for (Constructor c : activeConstructors) {
            if (!newActiveC.contains(c)) newInactiveC.add(c);
        }

        // remove

        activeConstructors = newActiveC;
        inactiveConstructors = newInactiveC;

        if (DEBUG) System.out.println("[DYNAMIC LIA]: newActiveC = " + newActiveC.size() + ", " + newActiveC);
        if (DEBUG) System.out.println("[DYNAMIC LIA]: newInactiveC = " + newInactiveC.size() + ", " + newInactiveC);

        // get all InputIndices of every ii in IC
        List<Integer> IX = new ArrayList<>();
        for (InputInformation ii : IC) {
            IX.add(ii.getIndex());
        }
        if (DEBUG) System.out.println("[DYNAMIC LIA]: IX = " + IX);

        // remove IC from lif0_ii
        List<InputInformation> lif0_ii = new ArrayList<>(lif0.getInputInformations());
        lif0_ii.removeAll(IC);


        // if |IX| > 1, then split up lif0 in lif0,1 and lif0,2
        if (IX.size() > 1) { // TODO: MARK! WAS size() > 1 ORIGINALLY!

            List<InputInformation> lif01_iis = new ArrayList<>();
            List<InputInformation> lif02_iis = new ArrayList<>();



            // for every InputInformation ii in IC:
            //          for every Inputinformation j in lif0_ii:
            //                  if constructor(j) == constructor(i) && index(j) < index(ii) -> lif01_iis.add(j)
            //                  if constructor(j) == constructor(i) && index(j) > index(ii) -> lif02_iis.add(j)
            for (InputInformation ii : IC) {
                for (InputInformation j : lif0_ii) {
                    if (j.getConstructor() == ii.getConstructor() && j.getIndex() < ii.getIndex()) {
                        lif01_iis.add(j);
                    }
                    else if (j.getConstructor() == ii.getConstructor() && j.getIndex() > ii.getIndex()) {
                        lif02_iis.add(j);
                    }
                }
            }
            if (DEBUG) System.out.println("[DYNAMIC LIA]: lif01_iis = " + lif01_iis);
            if (DEBUG) System.out.println("[DYNAMIC LIA]: lif02_iis = " + lif02_iis);

            // if |lif0,1| == 0 ->
            //      replace lif0 with lif0,2
            //      add setToSingle(ludeme) ABOVE lif0

            if(lif01_iis.size() == 0){
                // replace lif0 with lif0,2
                LInputField lif02 = new LInputField(LNC, lif02_iis);
                addInputFieldAbove(lif02, c_inputField);
                removeField(c_inputField);
                // add single inputfield
                LInputField single = new LInputField(LNC, IC.get(0));
                addInputFieldAbove(single, lif02);
                return single;
            }

            // if |lif0,2| == 0 ->
            //      replace lif0 with lif0,1
            //      add setToSingle(ludeme) BELOW lif0

            else if(lif02_iis.size() == 0){
                // replace lif0 with lif0,1
                LInputField lif01 = new LInputField(LNC, lif01_iis);
                addInputFieldAbove(lif01, c_inputField);
                removeField(c_inputField);
                // add single inputfield
                LInputField single = new LInputField(LNC, IC.get(0));
                addInputFieldBelow(single, lif01);
                return single;
            }

            // else ->
            //      replace lif0 with lif0,1
            //      add setToSingle(ludeme) BELOW lif0
            //      add lif0,2 BELOW setToSingle(ludeme)

            else {
                // replace lif0 with lif0,1
                LInputField lif01 = new LInputField(LNC, lif01_iis);
                addInputFieldAbove(lif01, c_inputField);
                removeField(c_inputField);
                // add single inputfield
                LInputField single = new LInputField(LNC, IC.get(0));
                addInputFieldBelow(single, lif01);
                // add lif0,2 BELOW setToSingle(ludeme)
                LInputField lif02 = new LInputField(LNC, lif02_iis);
                addInputFieldBelow(lif02, single);
                return single;
            }
        }
        // if |IX| == 1 ->
        else {
            System.out.println("[DYNAMIC LIA]: ERROR WOULD BE AVOIDED; ONLY ONE CONSTRUCTOR");
        }
        return null;
    }

    private void updateDynamic(){
        if(activeConstructors.size() == 1){
            getInputFields(LNC);
            drawInputFields();
            return;
        }

        // find inputinformations of active constructors
        List<InputInformation> activeInputInformations = new ArrayList<>();
        for(InputInformation ii : allInputInformations){
            if(activeConstructors.contains(ii.getConstructor())){
                activeInputInformations.add(ii);
            }
        }

        for(LInputField lif : inputFields){
            for(InputInformation ii : new ArrayList<>(lif.getInputInformations())){
                if(inactiveConstructors.contains(ii.getConstructor())){
                    lif.removeInputInformation(ii);
                }
                activeInputInformations.remove(ii);
            }
        }

        for(LInputField lif : inputFields){
            if(!lif.isSingle()){
                for(InputInformation ii : activeInputInformations){
                    lif.addInputInformation(ii);
                }
                return;
            }
        }
    }


    private void mergeOptionalArgumentsInOne(){
        List<LInputField> consequentOptionalInputs = new ArrayList<>();
        List<LInputField> newFields = new ArrayList<>();
        for(int i = 0; i < inputFields.size(); i++){
            LInputField inputField = inputFields.get(i);
            if((inputField.isSingle || LNC.getLudemeNode().getProvidedInputs()[inputField.getInputInformation().getIndex()] == null) && inputField.getInputInformation().isOptional()){
                consequentOptionalInputs.add(inputField);
            } else if(consequentOptionalInputs.size() == 1){
                newFields.add(consequentOptionalInputs.get(0));
                newFields.add(inputField);
                consequentOptionalInputs.clear();
            } else if(consequentOptionalInputs.size() > 1){
                // add merged input field
                List<InputInformation> additionalArguments = new ArrayList<>();
                for(LInputField inputFieldOptional : consequentOptionalInputs) {
                    additionalArguments.addAll(inputFieldOptional.getInputInformations());
                }
                newFields.add(new LInputField(LNC, additionalArguments));
                newFields.add(inputField);
                consequentOptionalInputs = new ArrayList<>();
            } else {
                newFields.add(inputField);
            }
        }
        if(consequentOptionalInputs.size() == 1) {
            newFields.add(consequentOptionalInputs.get(0));
            consequentOptionalInputs.clear();
        }
        if(consequentOptionalInputs.size() > 1){
            List<InputInformation> additionalArguments = new ArrayList<>();
            for(LInputField inputFieldOptional : consequentOptionalInputs){
                additionalArguments.add(inputFieldOptional.getInputInformation());
            }
            newFields.add(new LInputField(LNC, additionalArguments));
            consequentOptionalInputs = new ArrayList<>();
        }
        this.inputFields = newFields;
    }

    public void updateConstructor(){
        inputFields = getInputFields(LNC);
        drawInputFields();
    }

    public void updateProvidedInputs(){

        // Fill existing inputs
        Object[] providedInputs = LNC.getLudemeNode().getProvidedInputs();
        for(int input_index = 0; input_index < providedInputs.length; input_index++){
            Object providedInput = providedInputs[input_index];
            if(providedInput != null){
                // find the inputfield with same index
                LInputField inputField = null;
                for(LInputField lInputField : inputFields){
                    if(lInputField.getInputIndices().contains(input_index)){
                        inputField = lInputField;
                        break;
                    }
                }
                inputField.setUserInput(providedInput, input_index);
            }
        }

        drawInputFields();

        repaint();
        revalidate();
        setVisible(true);

    }

    public void addInputFieldAbove(LInputField newInputField, LInputField inputFieldAbove){
        inputFields.add(inputFields.indexOf(inputFieldAbove), newInputField);
        if(inputFieldAbove.getInputInformations().size() == 0) inputFields.remove(inputFieldAbove);
        drawInputFields();
        System.out.println("[LInputArea] Adding " + newInputField + " above " + inputFieldAbove);
    }

    public void addInputFieldBelow(LInputField newInputField, LInputField inputFieldBelow){
        inputFields.add(inputFields.indexOf(inputFieldBelow) + 1, newInputField);
        if(inputFieldBelow.getInputInformations().size() == 0) inputFields.remove(inputFieldBelow);
        drawInputFields();
        System.out.println("[LInputArea] Adding " + newInputField + " below " + inputFieldBelow);
    }

    public void removeField(LInputField inputField){
        inputFields.remove(inputField);
        drawInputFields();
    }

    public void updatePosition(){
        for(LInputField inputField : inputFields){
            if(inputField.getConnectionComponent() != null){
                inputField.getConnectionComponent().updatePosition();
            }
        }
    }

}
