package cinema.frontend.components;

import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import cinema.frontend.components.factory.SwingComponentFactory;
import cinema.frontend.windows.DashboardWindow;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CzPet
 */
public class ShowPanel extends JPanel{
  private final static Object[] SHOW_COLUMN_NAMES = new Object[]{"ID","Datetime", "Title", "Room", "Available seat"};
  
  private DashboardWindow screen;
  private JTable showTable;
  private JButton newShowButton, modifyShowButton, deleteShowButton, resetFilterButton, bookButton;
  private JComboBox filmComboBox, roomComboBox;

  public ShowPanel(DashboardWindow s) {
    this.screen = s;
    initShowPanel();
  }

  private void initShowPanel() {
    this.setLayout(new BorderLayout());
    
    initFilterPanel();
    initShowTable();
    initButtonsPanel();
   }

  private void initFilterPanel() {
    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    filmComboBox = SwingComponentFactory.createComboBox(filterPanel, "Film filter");
    GuiManager.listAllFilms().forEach(film -> filmComboBox.addItem(film.getTitle() + " (" + film.getFilmId() + ")"));
    filmComboBox.addActionListener(this::filmFilterSelect);
    
    roomComboBox = SwingComponentFactory.createComboBox(filterPanel, "Room filter");
    GuiManager.listAllRooms().forEach(room -> roomComboBox.addItem(room.getRoomName()));
    roomComboBox.addActionListener(this::roomFilterSelect);
    
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

  private void initButtonsPanel() {
    JPanel showButtonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    
    bookButton = SwingComponentFactory.createButton(showButtonsPanel, "Book");
    bookButton.addActionListener(this::booking);
    
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
  
  private void booking(ActionEvent event) {
    screen.addBookingPanelToTabbedPanel();
  }
  
  private void addNewShow(ActionEvent event) {
    screen.addNewShowPanelToTabbedPanel();
  }
  
  private void modifyShow(ActionEvent event) {
    
  }
  
  private void deleteShow(ActionEvent event) {
    
  }
  
  private void filmFilterSelect(ActionEvent event) {
    if(filmComboBox.getSelectedIndex() != 0) {
      roomComboBox.setSelectedIndex(0);
      String line = filmComboBox.getSelectedItem().toString();
      addContentToTable(GuiManager.listShowsByFilmId(line.substring(line.lastIndexOf('(')+1, line.length()-1)));
    }
    else if(roomComboBox.getSelectedIndex() == 0){
      addContentToTable(GuiManager.listAllShows());
    }
  }
  
  private void roomFilterSelect(ActionEvent event) {
    if(roomComboBox.getSelectedIndex() != 0) {
      filmComboBox.setSelectedIndex(0);
      addContentToTable(GuiManager.listShowsByRoom(roomComboBox.getSelectedItem().toString()));
    }
    else if(filmComboBox.getSelectedIndex() == 0) {
      addContentToTable(GuiManager.listAllShows());
    }
  }
  
  private void resetFilter(ActionEvent event) {
    filmComboBox.setSelectedIndex(0);
    roomComboBox.setSelectedIndex(0);
  }
}
