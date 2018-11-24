package cinema.frontend.components;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author CzPet
 */
public class SeatButton extends JButton {
  private final int row;
  private final int column;
  private int statudIdx;
          
  private static Color[] status = {Color.red, Color.green};
  
  public SeatButton(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }
  
  public Color getStatus() {
    return status[statudIdx];
  }
  
  public void setStatus(String st) {
    if(st.equals("A")) {
      statudIdx = 1;
    } else if (st.equals("O")) {
      statudIdx = 0;
    }
  }
  
  public Color changeStatus() {
    statudIdx = (statudIdx + 1)%2;
    return status[statudIdx];
  }
}
