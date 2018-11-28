package cinema.backend.entities;

/**
 *
 * @author CzP
 */
public class Seats{
    private long seatId;
    private long showId;
    private String roomName;
    private String seatsStatus;
    private int emptySeat;
    private int soldTicket;

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSeatsStatus() {
        return seatsStatus;
    }

    public void setSeatsStatus(String seatsStatus) {
        this.seatsStatus = seatsStatus;
        setEmptySeatAndSoldTicket();
    }
    
    public Object[] toArray() {
        String[] array = {Long.toString(seatId),
                          Long.toString(showId),
                          roomName,
                          seatsStatus
                          };
        return array;
    }

    @Override
    public String toString() {
        return "Seat{" + "seatId=" + seatId + ", showId=" + showId + ", roomName=" + roomName + ", seatsStatus=" + seatsStatus + '}';
    }

  private void setEmptySeatAndSoldTicket() {
    emptySeat = 0;
    soldTicket = 0;
    for(char ch : seatsStatus.toCharArray()) {
      if(ch == 'A') {
        emptySeat++;
      } else if(ch == 'O') {
        soldTicket++;
      }
    }
  }
  
  public int getEmptySeat() {
    return emptySeat;
  }
  
  public int getSoldTicket() {
    return  soldTicket;
  }
}
