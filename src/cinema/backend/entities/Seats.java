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
        //
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
}
