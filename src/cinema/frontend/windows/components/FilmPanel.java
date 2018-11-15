package cinema.frontend.windows.components;

import cinema.frontend.GuiManager;
import cinema.frontend.windows.DashboardWindow;
import cinema.frontend.windows.components.factory.SwingComponentFactory;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
    private FilmDetailsPanel detailsPanel;
    private DashboardWindow screen;
    
    public FilmPanel(DashboardWindow screen) {
        initFilmPanel();
        this.screen = screen;
    }

    private void initFilmPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initFilmTable();
        detailsPanel = new FilmDetailsPanel();
        this.add(detailsPanel);
        detailsPanel = new FilmDetailsPanel();
    }
    
    private void initFilmTable() {
        filmTable = new JTable(4, 5);
        filmTable.getSelectionModel().addListSelectionListener(this::newSelection);
        this.add(new JScrollPane(filmTable));
    }
    
    private void newSelection(ListSelectionEvent event) {
        detailsPanel.setFilm(filmTable.getSelectedRow());
        //send the title to the newShowPanel
    }
}
