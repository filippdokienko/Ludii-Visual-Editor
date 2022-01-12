import panels.MainPanel;
import panels.editor.EditorPanel;

import javax.swing.*;

public class MainFrame extends JFrame {

    private MainPanel main_panel;

    public MainFrame(){
        initialize();
    }

    private void initialize(){
        setTitle("Prototype");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        main_panel = new MainPanel();
        add(main_panel);

        setVisible(true);

    }



}
