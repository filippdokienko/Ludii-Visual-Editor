package components;

import grammar.Ludeme;
import panels.editor.EditorPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class AddLudemeWindow extends JPanel {

    DefaultListModel listModel;
    JTextField searchField;

    public AddLudemeWindow(List<Ludeme> ludemeList, EditorPanel editorPanel){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GREEN);

        searchField = new JTextField();


        listModel = new DefaultListModel<Ludeme>();
        for (Ludeme l : ludemeList) {
            listModel.addElement(l);
        }
        JList list = new JList(listModel);
        JScrollPane scrollableList = new JScrollPane(list);

        scrollableList.setPreferredSize(new Dimension(scrollableList.getPreferredSize().width, 150));
        searchField.setPreferredSize(new Dimension(scrollableList.getPreferredSize().width, searchField.getPreferredSize().height));


        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println(searchField.getText());
                listModel = new DefaultListModel<Ludeme>();
                for(Ludeme l : ludemeList){
                    // TODO: Improve
                    if(l.getName().contains(searchField.getText())){
                        listModel.addElement(l);
                    }
                }
                list.setModel(listModel);
                repaint();
            }
        });



        add(searchField); add(scrollableList);

        setPreferredSize(new Dimension(getPreferredSize()));
        setSize(getPreferredSize());
        setVisible(false);


        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        editorPanel.addLudeme((Ludeme) o);
                        searchField.setText("");
                    }
            }
        };
        list.addMouseListener(mouseListener);


    }
}
