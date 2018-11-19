package cinema.frontend.components;

import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author CzP
 */
public class BookingPanel extends JPanel {
  
  private JTabbedPane tabbedPane;
  private String showId;
  private JButton saveButton, dropButton, cancelButton;
  
  public BookingPanel(JTabbedPane tp, String showId) {
    tabbedPane = tp;
    this.showId = showId;
    
    initBookingpanel();
  }

  private void initBookingpanel() {
    setLayout(new BorderLayout());
//    initSeatRepPanel();
    initButtonsPanel();
  }

  private void initButtonsPanel() {
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    saveButton = SwingComponentFactory.createButton(buttonsPanel, "Save");
    saveButton.addActionListener(this::saveChanges);
    
    dropButton = SwingComponentFactory.createButton(buttonsPanel, "Drop");
    dropButton.addActionListener(this::dropChanges);
    
    cancelButton = SwingComponentFactory.createButton(buttonsPanel, "Cancel");
    cancelButton.addActionListener(this::cancel);
    
    add(buttonsPanel, BorderLayout.SOUTH);
  }
  
  private void saveChanges(ActionEvent event) {
    
  }
  private void dropChanges(ActionEvent event) {
    
  }
  
  private void cancel(ActionEvent event) {
    tabbedPane.remove(this);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
    tabbedPane.setEnabled(true);
  }
}
