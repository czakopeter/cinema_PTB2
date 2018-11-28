package cinema.frontend.components;

import cinema.frontend.components.factory.SeatButton;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
  private JButton saveButton, dropButton, cancelButton;
  private JPanel seatsPanel;
  private final Seats seats;
  private int rowLength;
  
  public BookingPanel(JTabbedPane tp, String filmId) {
    tabbedPane = tp;
    this.seats = GuiManager.getSeatsByShow(Long.valueOf(filmId));
    System.out.println(seats);
    initBookingpanel();
  }
  
  private void initBookingpanel() {
    setLayout(new BorderLayout());
    initInfoPanel();
    initSeatsPanel();
    initButtonsPanel();
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
    Show show = GuiManager.getShow(Long.toString(seats.getShowId()));
    return GuiManager.getFilm(Long.toString(show.getFilmId())).getTitle() +
           "    " + show.getStartDate().toString() + 
           " " + show.getStartTime().toString();
  }
  
  private void initSeatsPanel() {
    seatsPanel = new JPanel();
    initSeatsButton();
    setSeatsButton();
    add(seatsPanel, BorderLayout.CENTER);
  }
  
  private void initSeatsButton() {
    seatsPanel.removeAll();
    Room room = GuiManager.getRoom(seats.getRoomName());
    rowLength = room.getColumnNr();
    
    seatsPanel.setLayout(new GridLayout(room.getRowNr(), room.getColumnNr()));
    for(int row = 0; row<room.getRowNr(); ++row) {
      for(int column = 0; column< room.getColumnNr(); ++ column) {
        SeatButton sb = SwingComponentFactory.createSeatButton(row, column);
        sb.addActionListener(this::changeStatus);
        seatsPanel.add(sb);
      }
    }
  }
  
  private void setSeatsButton() {
    for(Component component : seatsPanel.getComponents()) {
      SeatButton sb = (SeatButton) component;
      sb.setStatus(seats.getSeatsStatus().charAt(sb.getRow()*rowLength+sb.getColumn()));
    }
  }

  private void changeStatus(ActionEvent event) {
    SeatButton sb = (SeatButton)event.getSource();
    sb.setBackground(sb.changeStatus());
    System.out.println(sb.getRow()*rowLength + sb.getColumn());
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
    if(updateSeatsState()) {
      GuiManager.modifyBooking(seats);
    }
  }
  
  private void dropChanges(ActionEvent event) {
    setSeatsButton();
  }
  
  private void cancel(ActionEvent event) {
    tabbedPane.remove(this);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
    tabbedPane.setEnabled(true);
  }

  private boolean updateSeatsState() {
    char[] chSeats = seats.getSeatsStatus().toCharArray();
    for(Component component : seatsPanel.getComponents()) {
      SeatButton sb = (SeatButton) component;
      chSeats[sb.getRow()*rowLength + sb.getColumn()] = sb.getSeatStatus();
    }
    if(seats.getSeatsStatus().equals(String.valueOf(chSeats))) {
      return false;
    }
    seats.setSeatsStatus(String.valueOf(chSeats));
    return true;
  }
}