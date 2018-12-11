package cinema.frontend.validators;

import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author CzP
 */
public class Validator {

  private final static Pattern DATE_PATTERN = Pattern.compile("\\d{4}[-]\\d{2}[-]\\d{2}");
  private final static Pattern TIME_PATTERN = Pattern.compile("\\d{2}[:]\\d{2}");
  private final static String AGE_LIMIT_2_MIN_START_TIME = "17:00";
  private final static String AGE_LIMIT_3_MIN_START_TIME = "21:00";
  
  public static boolean validateShow(Long filmId, String roomName, String startDate, String startTime, JFrame frame) {
    if(GuiManager.listShowsByFilmId(filmId).size() >= GuiManager.getFilm(filmId).getLicenseToPlay()) {
      JOptionPane.showMessageDialog(frame, "Ezt a filmet nem lehet tobszor vetiteni");
      return false;
    }
    
    if(startDate.isEmpty() || startTime.isEmpty())
    {
      JOptionPane.showMessageDialog(frame, "Hianyzo parameter");
      return false;
    }
    if(!DATE_PATTERN.matcher(startDate).matches()) {
      JOptionPane.showMessageDialog(frame, "Datum fomatum: 2018-10-10");
      return false;
    }
    if(!TIME_PATTERN.matcher(startTime).matches()) {
      JOptionPane.showMessageDialog(frame, "Ido fomatum: 10:10");
      return false;
    }
    if(0 < LocalDate.now().toString().compareTo(startDate)) {
      JOptionPane.showMessageDialog(frame, "Aktualis datumnal nem lehet regebben");
      return false;
    }
    if(0 == LocalDate.now().toString().compareTo(startDate) && 0 < LocalTime.now().toString().compareTo(startTime)) {
      JOptionPane.showMessageDialog(frame, "Aktualis idopontnal nem lehet regebben");
      return false;
    }
    
    switch(GuiManager.getFilm(filmId).getAgeLimit())
    {
      case AGE_LIMIT_2:
        if(0>startTime.compareTo(AGE_LIMIT_2_MIN_START_TIME)) {return false;}
        break;
      case AGE_LIMIT_3:
        if(0>startTime.compareTo(AGE_LIMIT_3_MIN_START_TIME)) {return false;}
        break;
    }
    
    int runtime = GuiManager.getFilm(filmId).getRuntime();
    int counter = 0;
    for(Show show : GuiManager.listShowsByFilmId(filmId)){
      if(show.getStartDate().equals(LocalDate.parse(startDate)) &&
        ((LocalTime.parse(startTime).isAfter(show.getStartTime()) &&
          LocalTime.parse(startTime).isBefore(show.getStartTime().plusMinutes(runtime))) ||
         (LocalTime.parse(startTime).plusMinutes(runtime).isAfter(show.getStartTime()) &&
          LocalTime.parse(startTime).plusMinutes(runtime).isBefore(show.getStartTime().plusMinutes(runtime)))))
      {
        ++counter;
        if(counter == 3) {
          return false;
        }
      }
    }
    return true;
  }
  
}
