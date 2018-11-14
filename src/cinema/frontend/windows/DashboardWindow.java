package cinema.frontend.windows;

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
    
    public DashboardWindow() {
        initScreen();
    }

    private void initScreen() {
//        setLayout(new FlowLayout());
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JPanel filmPanel = new JPanel();
        JPanel showPanel = new JPanel();
        
        tabbedPane.addTab("Films", filmPanel);
        tabbedPane.addTab("Shows", showPanel);
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
