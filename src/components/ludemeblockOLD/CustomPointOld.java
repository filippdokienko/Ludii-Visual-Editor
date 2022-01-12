package components.ludemeblockOLD;

import java.awt.*;

public class CustomPointOld {
    public int x, y;
    public CustomPointOld(int x, int y){
        this.x = x;
        this.y = y;
    }
    public CustomPointOld(Point p){
        this.x = (int) p.getX();
        this.y = (int) p.getY();
    }

    public void update(Point p){
        this.x = (int) p.getX();
        this.y = (int) p.getY();
    }

    public String toString(){
        return "[" + x + ", " + y + "]";
    }
}
