package cinema.backend.entities;

import cinema.backend.enums.SeatStatus;

/**
 *
 * @author CzP
 */
public class Seat {
    private long seatId;
    private long showId;
    private String roomName;
    private int row;
    private int column;
    private SeatStatus status;

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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    
    public Object[] toArray() {
        String[] array = {Long.toString(seatId),
                          Long.toString(showId),
                          roomName,
                          Integer.toString(row),
                          Integer.toString(column),
                          status.name()
                          };
        return array;
    }

    @Override
    public String toString() {
        return "Seat{" + "seatId=" + seatId + ", showId=" + showId + ", roomName=" + roomName + ", row=" + row + ", column=" + column + ", status=" + status + '}';
    }
}
