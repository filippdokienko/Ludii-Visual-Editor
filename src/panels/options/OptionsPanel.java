package panels.options;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    public OptionsPanel(){

        setLayout(new SpringLayout());

        JLabel title = new JLabel("OPTIONS", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        add(title);

        setBackground(Color.LIGHT_GRAY);

        add(Box.createRigidArea(new Dimension(0, 50)));

    }

}
