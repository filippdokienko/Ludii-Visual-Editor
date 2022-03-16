package LayoutManagement.interfaces;

import LayoutManagement.Math.Vector2D;

import java.util.List;

/**
 * An interface of a node used for layout graph
 * @author nic0gin
 */

public interface iGNode {

    int getId();

    iGNode getIGParent();

    List<iGNode> getChildren();

    List<iGNode> getSiblings();

    Vector2D getPos();

    void setPos();

}
