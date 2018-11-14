package cinema.frontend.windows;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author CzP
 */
public class FilmPanel extends JPanel {

    private JTable filmTable;
    private JPanel filmDetailsPanel;
    
    public FilmPanel() {
        initFilmPanel();
        initFilmDetailsPanel();
    }

    private void initFilmPanel() {
        filmTable = new JTable(4, 5);
        filmTable.getSelectionModel().addListSelectionListener(this::newSelection);
        add(new JScrollPane(filmTable));
    }
    
    private void newSelection(ListSelectionEvent event) {
        
    }

    private void initFilmDetailsPanel() {
        
    }
}
