package cinema.backend.entities;

import java.time.LocalDateTime;

/**
 *
 * @author CzP
 */
public class Show {
    private long showId;
    private long filmId;
    private LocalDateTime startAtDateTime;
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

    public LocalDateTime getStartAtDateTime() {
        return startAtDateTime;
    }

    public void setStartAtDateTime(LocalDateTime startAtDateTime) {
        this.startAtDateTime = startAtDateTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public Object[] toArray() {
        String[] array = {Long.toString(showId), Long.toString(filmId), startAtDateTime.toString(), roomName};
        return array;
    }

    @Override
    public String toString() {
        return "Show{" + "showId=" + showId + ", filmId=" + filmId + ", startAtDateTime=" + startAtDateTime + ", roomName=" + roomName + '}';
    }
}