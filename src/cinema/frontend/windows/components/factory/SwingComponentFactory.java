package cinema.frontend.windows.components.factory;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author CzP
 */
public final class SwingComponentFactory {

    private SwingComponentFactory() {
    }
    
    public static JButton createButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        panel.add(button);
        return button;
    }
}
