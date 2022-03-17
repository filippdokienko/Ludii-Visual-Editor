package model.MetaGraph;

import LayoutManagement.Math.Vector2D;
import model.interfaces.iGNode;

public class ForceNode {

    private Vector2D disp;
    private iGNode node;

    public ForceNode(iGNode node) {
        this.node = node;
    }

    public Vector2D getDisp() {
        return disp;
    }

    public void setDisp(Vector2D disp) {
        this.disp = disp;
    }

    public iGNode getNode() {
        return node;
    }
}
