package cinema.frontend.components.factory;

import cinema.frontend.components.SeatButton;
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
      comboBox.addItem("");
      JLabel label = new JLabel(text);
      panel.add(label);
      panel.add(comboBox);
      return comboBox;
    }
    
    public static JButton createSeatButton(int row, int column, String st) {
      SeatButton jb = new SeatButton(row, column);
      jb.setStatus(st);
      jb.setBackground(jb.getStatus());
      jb.setText(Integer.toString(row+1) + "/" + Integer.toString(column+1));
      return jb;
    }
}
