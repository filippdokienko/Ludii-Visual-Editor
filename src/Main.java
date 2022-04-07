import view.MainFrame;
import view.panels.editor.EditorPanel;
import view.panels.editor.EditorPanel2;

class Main {

    public static void main(String[] args){
        MainFrame f = new MainFrame(new EditorPanel2(5000,5000));
        MainFrame f2 = new MainFrame(new EditorPanel(5000,5000));
    }

}