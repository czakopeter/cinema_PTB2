package cinema.frontend.windows.components;

import cinema.backend.entities.Film;
import cinema.frontend.GuiManager;
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
//    private Film film;

  public FilmDetailsPanel() {
    initFilmDetailsPanel();
  }

  private void initFilmDetailsPanel() {
    setLayout(new FlowLayout(FlowLayout.LEADING));
    setToEmpty();
    add(poster_frame);
    add(dataPanel);
    
  }

  private void setToEmpty() {
    removeAll();
    poster_frame = new JLabel("POSTER");
    dataPanel = new JPanel();
  }

  void setFilm(String filmId) {
    Film film = GuiManager.getFilm(filmId);
    System.out.println(film.getPosterPath());
    BufferedImage poster = null;
    try{
      poster = ImageIO.read(new File(film.getPosterPath()));
    }
    catch(IOException ex) {System.err.println("prob");}

    if(poster != null) {
//      this.remove(poster_frame);
      this.add(poster_frame = new JLabel(new ImageIcon(poster)));
    }

  }
}
