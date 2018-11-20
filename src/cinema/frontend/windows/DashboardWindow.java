package cinema.frontend.windows;


import cinema.frontend.components.BookingPanel;
import cinema.frontend.components.FilmPanel;
import cinema.frontend.components.EditShowPanel;
import cinema.frontend.components.ShowPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author CzP
 */
public class DashboardWindow extends JFrame {
    private static JTabbedPane tabbedPane;
    
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
    JPanel filmListPanel = new FilmPanel();
    JPanel showListPanel = new ShowPanel(this);

    tabbedPane.addTab("Films list", filmListPanel);
    tabbedPane.addTab("Show list", showListPanel);
  }

  public void addEditShowPanelToTabbedPanel() {
    EditShowPanel nsp = new EditShowPanel(tabbedPane);
    tabbedPane.addTab("Add show", nsp);
    tabbedPane.setEnabled(false);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
  }
  
  public void addEditShowPanelToTabbedPanel(String showId) {
    EditShowPanel nsp = new EditShowPanel(tabbedPane, showId);
    tabbedPane.addTab("Add show", nsp);
    tabbedPane.setEnabled(false);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
  }

  public void addBookingPanelToTabbedPanel(String showId) {
    BookingPanel bp = new BookingPanel(tabbedPane, showId);
    tabbedPane.addTab("Booking tab", bp);
    tabbedPane.setEnabled(false);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
  }
}
