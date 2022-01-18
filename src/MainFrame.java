import components.AddLudemeWindow;
import grammar.parser.Parser;
import panels.MainPanel;
import panels.editor.EditorPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainPanel main_panel;

    public MainFrame(){
        initialize();
    }

    private void initialize(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}
        setTitle("Prototype");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        main_panel = new MainPanel();
        add(main_panel);

        //setLayout(new FlowLayout());
        //add(new AddLudemeWindow(100,100,new Parser().getLudemes()));

        setVisible(true);

    }



}
