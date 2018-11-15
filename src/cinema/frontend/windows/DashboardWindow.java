package cinema.frontend.windows;

import cinema.frontend.windows.components.FilmPanel;
import cinema.frontend.windows.components.NewShowPanel;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author CzP
 */
public class DashboardWindow extends JFrame {
    private static JTabbedPane tabbedPane;
    public static final int FILM_TAB = 0;
    public static final int SHOW_TAB = 1;
    public static final int NEW_SHOW_TAB = 2;
    
    private final static Object[] FILM_COLUMN_NAMES = new Object[]{"Title", "Sync", "Length", "Age limit"};
    private final static Object[] SHOW_COLUMN_NAMES = new Object[]{"Datetime", "Title", "Room", "Available seat"};
    
    public DashboardWindow() {
        initScreen();
    }

    private void initScreen() {
        setTitle("MULTIPLEX CINEMA");
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JPanel filmListPanel = new FilmPanel(this);
        JPanel showListPanel = new JPanel();
        JPanel newShowPanel = new NewShowPanel();
        
        tabbedPane.addTab("Films list", filmListPanel);
        tabbedPane.addTab("Show list", showListPanel);
        tabbedPane.addTab("New show", newShowPanel);
        add(tabbedPane);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void jumpNewShowPanelWithFilmTitle(String title) {
        tabbedPane.setSelectedIndex(NEW_SHOW_TAB);
    }
}
