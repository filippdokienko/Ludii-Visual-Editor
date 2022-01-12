package panels.editor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorGlassPanel extends JPanel {
    public EditorGlassPanel(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel l = new JLabel("3rfd");
                l.setLocation(e.getX(), e.getY());
                add(l);
                System.out.println("aaaaaaaaaaa");
                revalidate();
                repaint();
            }
        });
    }
}
