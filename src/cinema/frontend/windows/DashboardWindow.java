package cinema.frontend.windows;


import cinema.frontend.components.FilmPanel;
import cinema.frontend.components.NewShowPanel;
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
    private static JPanel addNewShowPanel;
    
    public DashboardWindow() {
        initScreen();
    }

  private void initScreen() {
    setTitle("MULTIPLEX CINEMA");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initTabbedPanel();
    initAddNewShowPanel();
    
    add(tabbedPane);
  }
    
  private void initTabbedPanel() {
    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    JPanel filmListPanel = new FilmPanel();
    JPanel showListPanel = new ShowPanel(this);

    tabbedPane.addTab("Films list", filmListPanel);
    tabbedPane.addTab("Show list", showListPanel);
  }

  private void initAddNewShowPanel() {
    addNewShowPanel = new NewShowPanel();
  }
}
