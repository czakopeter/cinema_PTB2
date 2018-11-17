package cinema.frontend.windows.components;

import cinema.backend.entities.Film;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author CzP
 */
public class FilmPanel extends JPanel {
  private final static Object[] FILM_COLUMN_NAMES = new Object[]{"ID","Title", "Sync", "Length", "Age limit"};

  private JTable filmTable;
  private FilmDetailsPanel detailsPanel;

  public FilmPanel() {
    initFilmPanel();
  }

  private void initFilmPanel() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    initFilmTable();
    detailsPanel = new FilmDetailsPanel();
    this.add(detailsPanel); 
  }

  private void initFilmTable() {

    filmTable = new JTable(5, 5);
    filmTable.getSelectionModel().addListSelectionListener(this::newSelection);
    DefaultTableModel dtm = new DefaultTableModel(FILM_COLUMN_NAMES, 0);
    
    this.add(new JScrollPane(filmTable));
  }
  
  public void addContentToTable(List<Film> content) {
    
  }

  private void newSelection(ListSelectionEvent event) {
    detailsPanel.setFilm("filmId");
    //save the selected film ID
  }
}
