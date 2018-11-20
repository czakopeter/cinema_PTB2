package cinema.frontend.components;

import cinema.frontend.components.factory.SwingComponentFactory;
import cinema.frontend.windows.DashboardWindow;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

/**
 *
 * @author CzP
 */
public class EditShowPanel extends JPanel{
  
  private JTabbedPane tabbedPane;
  
  private JComboBox filmComboBox, roomComboBox;
  private JButton addNewShowButton, cancelButton;

  public EditShowPanel(JTabbedPane tp) {
    tabbedPane = tp;
    initNewShowPanel();
  }
  
  private void initNewShowPanel() {
    setLayout(new BorderLayout());

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
    addNewShowButton.addActionListener(this::addShow);
    
    cancelButton = SwingComponentFactory.createButton(buttonsPanel, "Cancel");
    cancelButton.addActionListener(this::cancel);
    
    add(buttonsPanel, BorderLayout.SOUTH);
  }
  
  private void addShow(ActionEvent event) {
    
  }
  
  private void cancel(ActionEvent event) {
    tabbedPane.remove(this);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
    tabbedPane.setEnabled(true);
  }
}
