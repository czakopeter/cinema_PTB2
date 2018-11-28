package cinema.frontend.components;

import cinema.backend.entities.Film;
import cinema.frontend.GuiManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.ListSelectionModel;

/**
 *
 * @author CzP
 */
public class FilmPanel extends JPanel {
  private final static Object[] FILM_COLUMN_NAMES = new Object[]{"Title", "Sync", "Length", "Age limit", "Sold tickets"};
  private static List<String> listFilmTableId;

  private JTable filmTable;
  private FilmDetailsPanel detailsPanel;

  public FilmPanel() {
    initFilmPanel();
  }

  private void initFilmPanel() {
    this.setLayout(new BorderLayout());
    initFilmTable();
    initDetailsPanel();
  }
  
  private void initFilmTable() {
    listFilmTableId = new ArrayList<>();
    filmTable = new JTable(1, 1);
    filmTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    filmTable.setRowSelectionAllowed(true);
    filmTable.getSelectionModel().addListSelectionListener(this::newSelection);
    
    filmTable.setFillsViewportHeight(true);
    this.add(new JScrollPane(filmTable),BorderLayout.CENTER);
    addContentToTable(GuiManager.listAllFilms());
  }
  
  public void addContentToTable(List<Film> content) {
    listFilmTableId.clear();
    filmTable.removeAll();
    DefaultTableModel dtm = new DefaultTableModel(FILM_COLUMN_NAMES, 0);
    for(Film film : content) {
      listFilmTableId.add(Long.toString(film.getFilmId()));
      dtm.addRow(displayedFilmData(film));
    }
    
    filmTable.setModel(dtm);
  }
  
  private Object[] displayedFilmData(Film film) {
      Object[] array = {
        film.getTitle(),
        film.getSynconized().name(),
        Integer.toString(film.getRuntime()),
        film.getAgeLimit(),
        GuiManager.getSoldTicketsForFilm(Long.toString(film.getFilmId()))};
      return array;
  }
  

  private void newSelection(ListSelectionEvent event) {
    if(event.getValueIsAdjusting()) {
      detailsPanel.seDetailsPanelWithtFilm(listFilmTableId.get(filmTable.getSelectedRow()));
    }
  }
  
  private void initDetailsPanel() {
    detailsPanel = new FilmDetailsPanel();
    add(detailsPanel, BorderLayout.SOUTH); 
  }
}
