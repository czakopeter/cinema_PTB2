package cinema.frontend.validators;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author CzP
 */
public class Validator {

  public static boolean validateShow(Long filmId, String roomName, String startDate, String startTime, JFrame frame) {
    if(startDate.isEmpty() || startTime.isEmpty())
    {
      JOptionPane.showMessageDialog(frame, "Hiányzó paraméter");
      return false;
    }
    return true;
  }
  
}
