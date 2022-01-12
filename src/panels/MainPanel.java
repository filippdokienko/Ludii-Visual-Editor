package panels;

import panels.editor.EditorPanel;
import panels.options.OptionsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MainPanel extends JPanel {

    JPanel editor_panel = new EditorPanel(5000, 5000);
    JPanel options_panel = new OptionsPanel();

    public MainPanel(){
        setLayout(new BorderLayout());
        add(new JScrollPane(editor_panel), BorderLayout.CENTER);


        MouseAdapter ma = new MouseAdapter() {

            private Point origin;

            @Override
            public void mousePressed(MouseEvent e) {
                origin = new Point(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (origin != null) {
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, editor_panel);
                    if (viewPort != null) {
                        int deltaX = origin.x - e.getX();
                        int deltaY = origin.y - e.getY();

                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX;
                        view.y += deltaY;

                        editor_panel.scrollRectToVisible(view);
                    }
                }
            }

        };


        editor_panel.addMouseListener(ma);
        editor_panel.addMouseMotionListener(ma);
        //editor_panel.addMouseWheelListener(ma);

        options_panel.setPreferredSize(new Dimension(200,this.getHeight()));

        //add(options_panel, BorderLayout.EAST);
    }

}
