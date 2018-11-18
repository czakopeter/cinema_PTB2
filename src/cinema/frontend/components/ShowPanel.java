package cinema.frontend.components;

import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author CzPet
 */
public class ShowPanel extends JPanel{
  private final static Object[] SHOW_COLUMN_NAMES = new Object[]{"Datetime", "Title", "Room", "Available seat"};
  
  private JTable showTable;
  private JButton newShowButton, modifyShowButton, deleteShowButton, resetFilterButton;
  private JComboBox filmComboBox, roomComboBox;

  public ShowPanel() {
    initShowPanel();
  }

  private void initShowPanel() {
    String[] film = {"film1", "film2"};
    String[] room = {"room1", "room2"};
    this.setLayout(new BorderLayout());
    initFilterPanel();
    initTable();
    initShowButtonsPanel();
   }

  private void initFilterPanel() {
    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    resetFilterButton = new JButton("Filter reset");
    filmComboBox = SwingComponentFactory.createComboBox(filterPanel, "Film filter");
    roomComboBox = SwingComponentFactory.createComboBox(filterPanel, "Room filter");
    resetFilterButton = SwingComponentFactory.createButton(filterPanel, "Reset filter");
    add(filterPanel,BorderLayout.NORTH);
  }

  private void initTable() {
    showTable = new JTable(5, 5);
    add(new JScrollPane(showTable), BorderLayout.CENTER);
  }

  private void initShowButtonsPanel() {
    JPanel showButtonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    newShowButton = SwingComponentFactory.createButton(showButtonsPanel, "New show");
    modifyShowButton = SwingComponentFactory.createButton(showButtonsPanel, "Modify show");
    deleteShowButton = SwingComponentFactory.createButton(showButtonsPanel, "Delete show");
    add(showButtonsPanel, BorderLayout.SOUTH);
  }
  
  
}
