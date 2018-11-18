package cinema.frontend.components.factory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
    
    public static JComboBox createComboBox(JPanel panel, String text) {
      JComboBox comboBox = new JComboBox();
      JLabel label = new JLabel(text);
      panel.add(label);
      panel.add(comboBox);
      return comboBox;
    }
}
