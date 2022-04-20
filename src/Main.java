import view.MainFrame;
import view.panels.editor.EditorPanel;

import javax.swing.*;

class Main {

    private static final JPanel editPanel = new EditorPanel(5000,5000);

    public static void main(String[] args) {
        MainFrame f = new MainFrame(editPanel);
        //MainFrame f2 = new MainFrame(new EditorPanel(5000,5000));
    }

    public static void updatePanel()
    {
        editPanel.repaint();
        editPanel.revalidate();
    }

}