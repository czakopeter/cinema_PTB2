package cinema.frontend.windows;


import cinema.frontend.components.BookingPanel;
import cinema.frontend.components.FilmPanel;
import cinema.frontend.components.EditShowPanel;
import cinema.frontend.components.ShowPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author CzP
 */
public class DashboardWindow extends JFrame {
    private static JTabbedPane tabbedPane;
    FilmPanel filmListPanel;
    ShowPanel showListPanel;
    
    public DashboardWindow() {
        initScreen();
    }

  private void initScreen() {
    setTitle("MULTIPLEX CINEMA");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initTabbedPanel();
    
    add(tabbedPane);
  }
    
  private void initTabbedPanel() {
    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    filmListPanel = new FilmPanel();
    showListPanel = new ShowPanel(this);

    tabbedPane.addTab("Films list", filmListPanel);
    tabbedPane.addTab("Show list", showListPanel);
  }

  public void addEditShowPanelToTabbedPanel() {
    EditShowPanel nsp = new EditShowPanel(tabbedPane);
    tabbedPane.addTab("Add show", nsp);
    tabbedPane.setEnabled(false);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
  }
  
  public void addEditShowPanelToTabbedPanel(Long showId) {
    EditShowPanel nsp = new EditShowPanel(tabbedPane, showId);
    tabbedPane.addTab("Add show", nsp);
    tabbedPane.setEnabled(false);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
  }

  public void addBookingPanelToTabbedPanel(Long showId) {
    BookingPanel bp = new BookingPanel(tabbedPane, showId);
    tabbedPane.addTab("Booking tab", bp);
    tabbedPane.setEnabled(false);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
  }

  public void refreshListOfFilmAndShowPanel(Long showId, Long filmId) {
    showListPanel.refreshTable(showId);
    filmListPanel.refreshTable(filmId);
  }

  public void refreshListOfShow(Long showId) {
    showListPanel.refreshTable(showId);
  }
}
