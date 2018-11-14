package cinema.frontend.windows;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author CzP
 */
public class FilmPanel extends JPanel {

    private JTable filmTable;
    
    public FilmPanel() {
        initFilmPanel();
    }

    private void initFilmPanel() {
        filmTable = new JTable(4, 5);
        filmTable.getSelectionModel().addListSelectionListener(filmTable);
        filmTable.getSelectionModel().addListSelectionListener(this::newSelection);
        add(new JScrollPane(filmTable));
    }
}
