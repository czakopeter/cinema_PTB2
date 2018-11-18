package cinema.frontend.components;

import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CzPet
 */
public class ShowPanel extends JPanel{
  private final static Object[] SHOW_COLUMN_NAMES = new Object[]{"ID","Datetime", "Title", "Room", "Available seat"};
  
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
    initShowTable();
    initShowButtonsPanel();
   }

  private void initFilterPanel() {
    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    filmComboBox = SwingComponentFactory.createComboBox(filterPanel, "Film filter");
    GuiManager.listAllFilms().forEach(film -> filmComboBox.addItem(film.getTitle()));
    
    roomComboBox = SwingComponentFactory.createComboBox(filterPanel, "Room filter");
    
    resetFilterButton = SwingComponentFactory.createButton(filterPanel, "Reset filter");
    resetFilterButton.addActionListener(this::resetFilter);
    add(filterPanel,BorderLayout.NORTH);
  }

  private void initShowTable() {
    showTable = new JTable(1, 1);
    showTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    showTable.setRowSelectionAllowed(true);
    showTable.getSelectionModel().addListSelectionListener(this::newSelection);
    
    add(new JScrollPane(showTable), BorderLayout.CENTER);
    addContentToTable(GuiManager.listAllShows());
  }
  
  private void newSelection(ListSelectionEvent event) {
    if(event.getValueIsAdjusting()) {
      modifyShowButton.setEnabled(true);
      deleteShowButton.setEnabled(true);
    }
  }
  
  private void addContentToTable(List<Show> content) {
    showTable.removeAll();
    DefaultTableModel dtm = new DefaultTableModel(SHOW_COLUMN_NAMES, 0);
    content.forEach(row -> dtm.addRow(displayedShowData(row)));
    showTable.setModel(dtm);
  }
  
  private Object[] displayedShowData(Show row) {
    Object[] array = {Long.toString(row.getShowId()),
                      row.getStartAtDate().toString(),
                      GuiManager.getFilm(Long.toString(row.getFilmId())).getTitle(),
                      row.getRoomName()};
//                      GuiManager.getEmptySeatForShow(Long.toString(row.getShowId()));
    
    return array;
  }

  private void initShowButtonsPanel() {
    JPanel showButtonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    newShowButton = SwingComponentFactory.createButton(showButtonsPanel, "New show");
    newShowButton.addActionListener(this::addNewShow);
    
    modifyShowButton = SwingComponentFactory.createButton(showButtonsPanel, "Modify show");
    modifyShowButton.addActionListener(this::modifyShow);
    modifyShowButton.setEnabled(false);
    
    deleteShowButton = SwingComponentFactory.createButton(showButtonsPanel, "Delete show");
    deleteShowButton.addActionListener(this::deleteShow);
    deleteShowButton.setEnabled(false);
    
    add(showButtonsPanel, BorderLayout.SOUTH);
  }
  
  private void addNewShow(ActionEvent event) {
    
  }
  
  private void modifyShow(ActionEvent event) {
    
  }
  
  private void deleteShow(ActionEvent event) {
    
  }
  
  private void resetFilter(ActionEvent event) {
    filmComboBox.setSelectedIndex(0);
    roomComboBox.setSelectedIndex(0);
  }
}
