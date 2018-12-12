package cinema.frontend.components;

import cinema.backend.entities.Film;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import cinema.frontend.components.factory.SwingComponentFactory;
import cinema.frontend.windows.DashboardWindow;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
  private final static Object[] SHOW_COLUMN_NAMES = new Object[]{"Datetime", "Title", "Room", "Available seat"};
  private static List<Long> listFilmFilterId;
  private static List<Long> listShowTableId;
  
  private final DashboardWindow screen;
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
    
    listFilmFilterId = new ArrayList<>();
    filmComboBox = SwingComponentFactory.createComboBox(filterPanel, "Film filter");
    for(Film film : GuiManager.listAllFilms()) {
      listFilmFilterId.add(film.getFilmId());
      filmComboBox.addItem(film.getTitle());
    }
    filmComboBox.setSelectedIndex(-1);
    filmComboBox.addActionListener(this::filmFilterSelect);
    
    roomComboBox = SwingComponentFactory.createComboBox(filterPanel, "Room filter");
    GuiManager.listAllRooms().forEach(room -> roomComboBox.addItem(room.getRoomName()));
    roomComboBox.setSelectedIndex(-1);
    roomComboBox.addActionListener(this::roomFilterSelect);
    
    resetFilterButton = SwingComponentFactory.createButton(filterPanel, "Reset filter");
    resetFilterButton.addActionListener(this::resetFilter);
    add(filterPanel,BorderLayout.NORTH);
  }

  private void initShowTable() {
    listShowTableId = new ArrayList<>();
    showTable = new JTable(1, 1);
    showTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    showTable.setRowSelectionAllowed(true);
    showTable.getSelectionModel().addListSelectionListener(this::newSelection);
    
    add(new JScrollPane(showTable), BorderLayout.CENTER);
    addContentToTable(GuiManager.listAllShows());
  }
  
  private void addContentToTable(List<Show> content) {
    listShowTableId.clear();
    showTable.removeAll();
    DefaultTableModel dtm = new DefaultTableModel(SHOW_COLUMN_NAMES, 0);
    
    for(Show show : content) {
      System.out.println(show.getShowId());
      listShowTableId.add(show.getShowId());
      dtm.addRow(displayedShowData(show));
    }
    showTable.setModel(dtm);
  }
  
  private void newSelection(ListSelectionEvent event) {
    if(event.getValueIsAdjusting()) {
      Show show = GuiManager.getShow(listShowTableId.get(showTable.getSelectedRow()));
      if(0 > LocalDate.now().compareTo(show.getStartDate()) ||
          (0 == LocalDate.now().compareTo(show.getStartDate()) && 
           0 > LocalTime.now().compareTo(show.getStartTime()))) {
        bookButton.setEnabled(true);
        modifyShowButton.setEnabled(true);
        deleteShowButton.setEnabled(true);
      }
      else
      {
        bookButton.setEnabled(false);
        modifyShowButton.setEnabled(false);
        deleteShowButton.setEnabled(false);
      }
    }
  }
  
  private Object[] displayedShowData(Show row) {
    Object[] array = {
      row.getStartDate() + " " + row.getStartTime(),
      GuiManager.getFilm(Long.toString(row.getFilmId())).getTitle(),
      row.getRoomName(),
      GuiManager.getSeatsByShow(row.getShowId()).getEmptySeat()};
    return array;
  }

  private void initButtonsPanel() {
    JPanel showButtonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    
    bookButton = SwingComponentFactory.createButton(showButtonsPanel, "Book seat");
    bookButton.addActionListener(this::booking);
    bookButton.setEnabled(false);
    
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
    screen.addBookingPanelToTabbedPanel(listShowTableId.get(showTable.getSelectedRow()));
  }
  
  private void addNewShow(ActionEvent event) {
    screen.addEditShowPanelToTabbedPanel();
  }
  
  private void modifyShow(ActionEvent event) {
    screen.addEditShowPanelToTabbedPanel(listShowTableId.get(showTable.getSelectedRow()));
  }
  
  private void deleteShow(ActionEvent event) {
    GuiManager.deleteShow(listShowTableId.get(showTable.getSelectedRow()));
  }
  
  private void filmFilterSelect(ActionEvent event) {
    if(filmComboBox.getSelectedIndex() != -1) {
      roomComboBox.setSelectedIndex(-1);
      addContentToTable(GuiManager.listShowsByFilmId(listFilmFilterId.get(filmComboBox.getSelectedIndex())));
    }
  }
  
  private void roomFilterSelect(ActionEvent event) {
    if(roomComboBox.getSelectedIndex() != -1) {
      filmComboBox.setSelectedIndex(-1);
      addContentToTable(GuiManager.listShowsByRoom(roomComboBox.getSelectedItem().toString()));
    }
  }
  
  private void resetFilter(ActionEvent event) {
    filmComboBox.setSelectedIndex(-1);
    roomComboBox.setSelectedIndex(-1);
    refreshTable();
  }

  public void refreshTable(Long showId) {
    Seats seat = GuiManager.getSeatsByShow(showId);
    if(seat == null) {
      int row = listShowTableId.indexOf(showId);
      listShowTableId.remove(showId);
      showTable.remove(row);
    }
    else if(listShowTableId.contains(showId)) {
      int row = listShowTableId.indexOf(showId);
      showTable.setValueAt(seat.getEmptySeat(), row, showTable.getColumnCount()-1);
    }
    else {
      Show show = GuiManager.getShow(showId);
      listShowTableId.add(show.getShowId());
      DefaultTableModel dtm = (DefaultTableModel) showTable.getModel();
      dtm.addRow(displayedShowData(show));
    }
  }

  private void refreshTable() {
    addContentToTable(GuiManager.listAllShows());
  }
}
