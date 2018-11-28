package cinema.frontend.components;

import cinema.backend.entities.Film;
import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import cinema.frontend.components.factory.SwingComponentFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author CzP
 */
public class EditShowPanel extends JPanel{
  
  private final JTabbedPane tabbedPane;
  
  private JComboBox filmComboBox, roomComboBox;
  private static List<Long> listFilmFilterId;
  private JTextField startDate, startTime;
  private JButton saveShowButton, cancelButton;
  
  private Show show;

  public EditShowPanel(JTabbedPane tp) {
    tabbedPane = tp;
    initNewShowPanel();
  }
  
  public EditShowPanel(JTabbedPane tp, String showId) {
    tabbedPane = tp;
    initNewShowPanel();
    show = GuiManager.getShow(showId);
    setPanelComponents(show);
  }
  
  private void initNewShowPanel() {
    setLayout(new BorderLayout());

    initInputPanel();
    initButtonsPanel();
  }

  private void initInputPanel() {
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    
    listFilmFilterId = new ArrayList<>();
    filmComboBox = SwingComponentFactory.createComboBox(inputPanel, "Select film");
    for(Film film : GuiManager.listAllFilms()) {
      listFilmFilterId.add(film.getFilmId());
      filmComboBox.addItem(film.getTitle());
    }
    
    roomComboBox = SwingComponentFactory.createComboBox(inputPanel, "Select room");
    GuiManager.listAllRooms().forEach(room -> roomComboBox.addItem(room.getRoomName()));
    
    startDate = SwingComponentFactory.createTextField(inputPanel, "Date");
    startTime = SwingComponentFactory.createTextField(inputPanel, "Time");
    
    add(inputPanel, BorderLayout.NORTH);
  }

  private void initButtonsPanel() {
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
    saveShowButton = SwingComponentFactory.createButton(buttonsPanel, "Save show");
    saveShowButton.addActionListener(this::saveShow);
    
    cancelButton = SwingComponentFactory.createButton(buttonsPanel, "Cancel");
    cancelButton.addActionListener(this::cancel);
    
    add(buttonsPanel, BorderLayout.SOUTH);
  }
  
  private void setPanelComponents(Show show) {
    filmComboBox.setSelectedIndex(listFilmFilterId.indexOf(show.getFilmId()+1));
    roomComboBox.setSelectedItem(show.getRoomName());
    startDate.setText(show.getStartDate().toString());
    startTime.setText(show.getStartTime().toString());
  }
  
  private void saveShow(ActionEvent event) {
    if(show == null) {
      saveNewShow();
    } else {
      modifyShow();
    }
  }
  private void saveNewShow() {
    GuiManager.saveShow(
          listFilmFilterId.get(filmComboBox.getSelectedIndex()),
          roomComboBox.getSelectedItem().toString(),
          startDate.getText(), 
          startTime.getText());
  }
  
  private void modifyShow() {
    GuiManager.updateShow(
            show.getShowId(),
            listFilmFilterId.get(filmComboBox.getSelectedIndex()),
            roomComboBox.getSelectedItem().toString(),
            startDate.getText(), 
            startTime.getText());
  }
  
  private void cancel(ActionEvent event) {
    tabbedPane.remove(this);
    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
    tabbedPane.setEnabled(true);
  }
}
