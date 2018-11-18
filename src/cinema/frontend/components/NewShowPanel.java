package cinema.frontend.components;

import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author CzP
 */
public class NewShowPanel extends JPanel{
  private JComboBox filmComboBox, roomComboBox;
  private JButton addNewShowButton, cancelAddButton;

  public NewShowPanel() {
    initNewShowPanel();
  }
  
  private void initNewShowPanel() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(453, 691));
    initInputPanel();
    initButtonsPanel();
  }

  private void initInputPanel() {
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    
    filmComboBox = SwingComponentFactory.createComboBox(inputPanel, "Select film");
    roomComboBox = SwingComponentFactory.createComboBox(inputPanel, "Select room");
    
    add(inputPanel, BorderLayout.NORTH);
  }

  private void initButtonsPanel() {
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    addNewShowButton = SwingComponentFactory.createButton(buttonsPanel, "Add show");
    cancelAddButton = SwingComponentFactory.createButton(buttonsPanel, "Cancel");
    
    add(buttonsPanel, BorderLayout.SOUTH);
  }
}
