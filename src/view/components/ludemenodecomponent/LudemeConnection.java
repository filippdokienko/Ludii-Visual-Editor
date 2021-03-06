package view.components.ludemenodecomponent;

import view.components.ludemenodecomponent.inputs.LConnectionComponent;
import view.components.ludemenodecomponent.inputs.LIngoingConnectionComponent;

public class LudemeConnection {

    private final LConnectionComponent CONNECTION_COMPONENT;
    private final LIngoingConnectionComponent INGOING_CONNECTION_COMPONENT;

    private static int ID_COUNT = 0;
    private int ID = ID_COUNT++;

    private ImmutablePoint inputPoint;
    private ImmutablePoint targetPoint;

    public LudemeConnection(LConnectionComponent connectionComponent, LIngoingConnectionComponent ingoingConnectionComponent){
        this.CONNECTION_COMPONENT = connectionComponent;
        this.INGOING_CONNECTION_COMPONENT = ingoingConnectionComponent;


        this.inputPoint = connectionComponent.getConnectionPointPosition();
        this.targetPoint = ingoingConnectionComponent.getConnectionPointPosition();
    }

    public ImmutablePoint getInputPosition(){
        CONNECTION_COMPONENT.updatePosition();
        return inputPoint;
    }

    public ImmutablePoint getTargetPosition(){
        INGOING_CONNECTION_COMPONENT.updatePosition();
        return targetPoint;
    }

    public LIngoingConnectionComponent getIngoingConnectionComponent(){
        return INGOING_CONNECTION_COMPONENT;
    }

    public LConnectionComponent getConnectionComponent(){
        return CONNECTION_COMPONENT;
    }

    @Override
    public String toString(){
        return "Connection " + ID;
    }

}
