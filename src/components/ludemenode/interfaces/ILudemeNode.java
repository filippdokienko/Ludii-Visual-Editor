package components.ludemenode.interfaces;

import grammar.Constructor;
import grammar.Ludeme;

/**
 * Interface for Ludeme nodes
 */

public interface ILudemeNode {
    void initialize();
    int getWidth();
    void setWidth(int width);
    int getHeight();
    Ludeme getLudeme();
    Constructor getCurrentConstructor();
    void setCurrentConstructor(Constructor c);
}
