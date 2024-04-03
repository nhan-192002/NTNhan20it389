import javax.swing.SwingUtilities;
import model.TextModel;
import view.TextView;
import controller.TextController;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextView view = new TextView();
            TextModel model = new TextModel();
            new TextController(view, model);
            view.setVisible(true);
        });
    }
}
