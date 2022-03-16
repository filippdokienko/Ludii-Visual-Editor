package model;

import LayoutManagement.Math.Vector2D;
import LayoutManagement.interfaces.iGNode;
import grammar.Constructor;
import grammar.Ludeme;
import grammar.input.Input;
import model.interfaces.iLudemeNode;

import java.util.List;

/**
 * Node representation of a ludeme in the current description
 * @author Filipp Dokienko
 */

public class LudemeNode implements iLudemeNode, iGNode {

    private static int LAST_ID = 0;
    private final int ID;

    private final Ludeme LUDEME;
    private Constructor currentConstructor;
    private Object[] providedInputs;

    private int x,y;

    public LudemeNode(Ludeme ludeme, int x, int y){
        LAST_ID++;
        this.ID = LAST_ID;

        this.LUDEME = ludeme;
        this.currentConstructor = ludeme.getConstructors().get(0); // automatically first one
        this.providedInputs = new Object[currentConstructor.getInputs().size()];
        this.x = x;
        this.y = y;
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public iGNode getParent() {
        return null;
    }

    @Override
    public List<iGNode> getChildren() {
        return null;
    }

    @Override
    public List<iGNode> getSiblings() {
        return null;
    }

    @Override
    public Vector2D getPos() {
        return new Vector2D(x, y);
    }

    @Override
    public void setPos() {
        // TODO
    }

    // TODO: Should be probably in iGNode ?
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Ludeme getLudeme() {
        return this.LUDEME;
    }

    @Override
    public Constructor getCurrentConstructor() {
        return currentConstructor;
    }

    @Override
    public void setCurrentConstructor(Constructor selectedConstructor) {
        this.currentConstructor = selectedConstructor;
        // update providedInputs size
        this.providedInputs = new Object[currentConstructor.getInputs().size()];
    }

    @Override
    public Object[] getProvidedInputs() {
        return providedInputs;
    }

    @Override
    public void setProvidedInput(int index, Object providedInput) {
        providedInputs[index] = providedInput;
    }

    @Override
    public void setProvidedInput(Input input, Object providedInput) {
        // TODO
    }


    @Override
    public String getStringRepresentation() {
        StringBuilder s = new StringBuilder("(");
        s.append(getLudeme().getName());
        s.append(" ");
        s.append(getCurrentConstructor().getName());
        s.append(" ");
        for(Object o : getProvidedInputs()){
            if(o == null); // TODO: What to do when input is empty?
            else if(o instanceof String) s.append("'").append(o.toString()).append("'");
            else s.append(o.toString());
            s.append(" ");
        }
        s.append(")");
        return s.toString().trim();
    }
}
