package cinema.backend.entities;

import java.time.LocalDate;

/**
 *
 * @author CzP
 */
public class Show {
    private long showId;
    private long filmId;
    private LocalDate startAtDate;
    private String roomName;

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public LocalDate getStartAtDate() {
        return startAtDate;
    }

    public void setStartAtDate(LocalDate startAtDate) {
        this.startAtDate = startAtDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public Object[] toArray() {
        String[] array = {Long.toString(showId), Long.toString(filmId), startAtDate.toString(), roomName};
        return array;
    }

    @Override
    public String toString() {
        return "Show{" + "showId=" + showId + ", filmId=" + filmId + ", startAtDate=" + startAtDate + ", roomName=" + roomName + '}';
    }
}