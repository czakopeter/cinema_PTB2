package cinema.frontend.components;

import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seat;
import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javafx.scene.layout.Pane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

/**
 *
 * @author CzP
 */
public class BookingPanel extends JPanel {
  
  private final JTabbedPane tabbedPane;
  private final String showId;
  private JButton saveButton, dropButton, cancelButton;
  
  public BookingPanel(JTabbedPane tp, String showId) {
    tabbedPane = tp;
    this.showId = showId;
    
    initBookingpanel();
  }

  private void initInfoPanel() {
    JPanel infoPanel = new JPanel(new BorderLayout());
    
    JLabel info = new JLabel(getInfo(), SwingConstants.CENTER);
    
    JLabel sc = new JLabel("SCREEN", SwingConstants.CENTER);
    sc.setOpaque(true);
    sc.setBackground(Color.lightGray);
    
    infoPanel.add(info,BorderLayout.CENTER);
    infoPanel.add(sc,BorderLayout.SOUTH);
    add(infoPanel, BorderLayout.NORTH);
  }
  
  private String getInfo() {
    Show show = GuiManager.getShow(showId);
    return GuiManager.getFilm(Long.toString(show.getFilmId())).getTitle() +
           "    " + show.getStartDate().toString() + 
           " " + show.getStartTime().toString();
  }
  
  private void initBookingpanel() {
    setLayout(new BorderLayout());
    initInfoPanel();
    initSeatRepPanel();
    initButtonsPanel();
  }
  
  private void initSeatRepPanel() {
    Room room = GuiManager.getRoom(GuiManager.getShow(showId).getRoomName());
    JPanel seatsRepPanel = initSeatsRepPanel(room);
    
    add(seatsRepPanel, BorderLayout.CENTER);
  }
  
  private JPanel initSeatsRepPanel(Room room) {
    JPanel panel = new JPanel(new GridLayout(room.getRowNr(), room.getColumnNr()));
    for(int row = 0; row<room.getRowNr(); ++row) {
      for(int column = 0; column< room.getColumnNr(); ++ column) {
        JButton jb = new MyButton();
        jb.setBackground(Color.red);
        panel.add(jb);
      }
    }
    return panel;
  }

  private void initButtonsPanel() {
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    saveButton = SwingComponentFactory.createButton(buttonsPanel, "Save");
    saveButton.addActionListener(this::saveChanges);
    
    dropButton = SwingComponentFactory.createButton(buttonsPanel, "Drop");
    dropButton.addActionListener(this::dropChanges);
    
    cancelButton = SwingComponentFactory.createButton(buttonsPanel, "Cancel");
    cancelButton.addActionListener(this::cancel);
    
    add(buttonsPanel, BorderLayout.SOUTH);
  }
  
  private void saveChanges(ActionEvent event) {
    GuiManager.modifyBooking(getSeatsState());
  }
  
  private void dropChanges(ActionEvent event) {
    initBookingpanel();
  }
  
  private void cancel(ActionEvent event) {
    tabbedPane.remove(this);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
    tabbedPane.setEnabled(true);
  }

  private List<Seat> getSeatsState() {
    //gets rep of seats actual state from seatTable
    return null;
  }
}

//  List<Seat> seats = GuiManager.listSeatsByShow();