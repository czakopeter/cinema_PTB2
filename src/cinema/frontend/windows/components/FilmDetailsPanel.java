package cinema.frontend.windows.components;

import cinema.backend.entities.Film;
import cinema.frontend.GuiManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    setLayout(new FlowLayout(FlowLayout.LEADING));
    setPreferredSize(new Dimension(182, 268));
    setToEmpty();
    add(poster_frame);
    add(dataPanel);
    
  }

  private void setToEmpty() {
    poster_frame = new JLabel("POSTER");
    dataPanel = new JPanel();
  }

  void setFilm(String filmId) {
    Film film = GuiManager.getFilm(filmId);
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
}
