package cinema.frontend.components.factory;

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
  private final int colorsNr = status.length;
  
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
  
  public Color getColorStatus() {
    return status[statudIdx];
  }
  
  public Character getSeatStatus() {
    switch (statudIdx) {
      case 0:
        return 'O';
      case 1:
        return 'A';
      default:
        return null;
    }
  }
  
  public void setStatus(char st) {
    if(st == 'A') {
      statudIdx = 1;
    } else if (st == 'O') {
      statudIdx = 0;
    }
  }
  
  public Color changeStatus() {
    statudIdx = (statudIdx + 1)%colorsNr;
    return status[statudIdx];
  }
}
