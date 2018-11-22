package cinema.frontend.components;

import cinema.backend.entities.Film;
import cinema.frontend.GuiManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author CzP
 */
public class FilmDetailsPanel extends JPanel {
  private JLabel poster_frame;
  private JPanel dataPanel;

  public FilmDetailsPanel() {
    initFilmDetailsPanel();
  }

  private void initFilmDetailsPanel() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(182, 268));
    setToEmpty();
    add(poster_frame, BorderLayout.WEST);
    add(dataPanel, BorderLayout.CENTER);
    
  }

  private void setToEmpty() {
    poster_frame = new JLabel("POSTER");
    dataPanel = new JPanel();
  }

  void seDetailsPanelWithtFilm(String filmId) {
    Film film = GuiManager.getFilm(filmId);
    setPoster(film);
    setDatePanel(film);
    
  }

  private void setPoster(Film film) {
    BufferedImage poster = null;
    try{
      poster = ImageIO.read(new File(film.getPosterPath()));
    }
    catch(IOException ex) {
      poster_frame.setIcon(null);
      poster_frame.setText("NO POSTER");
    }

    if(poster != null) {
      poster_frame.setText(null);
      poster_frame.setIcon(new ImageIcon(poster));
    }
  }

  private void setDatePanel(Film film) {
    dataPanel.setLayout(new BorderLayout());
    JTextArea data = new JTextArea(film.toString());
    data.setLineWrap(true);
    dataPanel.add(data, BorderLayout.CENTER);
  }
}
