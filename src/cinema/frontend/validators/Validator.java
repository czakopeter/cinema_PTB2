package cinema.frontend.validators;

import cinema.backend.entities.Show;
import cinema.frontend.GuiManager;
import java.time.DateTimeException;
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
    LocalDate date;
    LocalTime time;
    try{
      date = LocalDate.parse(startDate);
      time = LocalTime.parse(startTime);
    } catch(DateTimeException e) {
      JOptionPane.showMessageDialog(frame, "Nem megfelelo a datum vagy az ido erteke\n datum: " +
              startDate + ", ido: " + startTime);
      return false;
    }
    if(LocalDate.now().isAfter(date)) {
      JOptionPane.showMessageDialog(frame, "Aktualis datumnal (" + LocalDate.now() + ") nem lehet regebben\n");
      return false;
    }
    if(LocalDate.now().equals(date) && LocalTime.now().isAfter(time)) {
      JOptionPane.showMessageDialog(frame, "Aktualis idopontnal (" + LocalTime.now() + ") nem lehet regebben");
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
    
    for(Show show : GuiManager.listShowsByRoomAtDate(roomName, startDate))
    {
      if((time.isAfter(show.getStartTime()) && time.isBefore(show.getStartTime().plusMinutes(runtime+30))) ||
         (time.plusMinutes(runtime+30).isAfter(show.getStartTime()) && time.plusMinutes(runtime).isBefore(show.getStartTime().plusMinutes(runtime)))){
        JOptionPane.showMessageDialog(frame, "Mar van masik eloadas ebben a teremben, ebben az idopontban");
        return false;
      }
    }
    
    int counter = 0;
    for(Show show : GuiManager.listShowsByFilmIdAtDate(filmId, startDate)){
      if((time.isAfter(show.getStartTime()) && time.isBefore(show.getStartTime().plusMinutes(runtime))) ||
         (time.plusMinutes(runtime).isAfter(show.getStartTime()) && time.plusMinutes(runtime).isBefore(show.getStartTime().plusMinutes(runtime))))
      {
        ++counter;
        if(counter == 3) {
          JOptionPane.showMessageDialog(frame, "Ezt a filmet ebben az idopontban mar 3 teremben jatszak");
          return false;
        }
      }
    }
    return true;
  }
}
