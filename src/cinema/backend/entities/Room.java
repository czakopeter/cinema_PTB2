package cinema.backend.entities;

/**
 *
 * @author CzP
 */
public class Room {
    private String roomName;
    private int rowNr;
    private int columnNr;

    public String getNameRoom() {
        return roomName;
    }

    public void setNameRoom(String roomName) {
        this.roomName = roomName;
    }

    public int getRowNr() {
        return rowNr;
    }

    public void setRowNr(int rowNr) {
        this.rowNr = rowNr;
    }

    public int getColumnNr() {
        return columnNr;
    }

    public void setColumnNr(int columnNr) {
        this.columnNr = columnNr;
    }

    public Object[] toArray() {
        String[] array = {roomName,
                          Integer.toString(rowNr),
                          Integer.toString(columnNr)};
        return array;
    }
    
    @Override
    public String toString() {
        return "Room{" + "nameRoom=" + roomName + ", rowNr=" + rowNr + ", columnNr=" + columnNr + '}';
    }
}
