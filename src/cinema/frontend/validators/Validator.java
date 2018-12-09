package cinema.frontend.validators;

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
    if(0 < LocalTime.now().toString().compareTo(startDate)) {
      JOptionPane.showMessageDialog(frame, "Aktualis idopontnal nem lehet regebben");
      return false;
    }
    
    switch(GuiManager.getFilm(filmId).getAgeLimit())
    {
      case AGE_LIMIT_2:
        return 0<startTime.compareTo(AGE_LIMIT_2_MIN_START_TIME);
      case AGE_LIMIT_3:
        break;
          
    }
    
    return true;
  }
  
}
