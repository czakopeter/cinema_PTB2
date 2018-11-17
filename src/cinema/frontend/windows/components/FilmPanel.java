package cinema.frontend.windows.components;

import cinema.backend.entities.Film;
import cinema.frontend.GuiManager;
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
    filmTable = new JTable(1, 1);
    filmTable.getSelectionModel().addListSelectionListener(this::newSelection);
    this.add(new JScrollPane(filmTable));
    addContentToTable(GuiManager.listAllFilms());
  }
  
  public void addContentToTable(List<Film> content) {
    filmTable.removeAll();
    DefaultTableModel dtm = new DefaultTableModel(FILM_COLUMN_NAMES, 0);
    content.forEach(row -> dtm.addRow(row.toArray()));
    filmTable.setModel(dtm);
  }

  private void newSelection(ListSelectionEvent event) {
    detailsPanel.setFilm((String)filmTable.getValueAt(filmTable.getSelectedRow(), 0));
    //save the selected film ID
  }
}
