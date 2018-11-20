package cinema.backend.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author CzP
 */
public class Show {
    private long showId;
    private long filmId;
    private LocalDate startDate;
    private LocalTime startTime;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public Object[] toArray() {
        String[] array = {Long.toString(showId), Long.toString(filmId), startDate.toString(), startTime.toString(), roomName};
        return array;
    }

    @Override
    public String toString() {
        return "Show{" + "showId=" + showId + ", filmId=" + filmId + ", startDate=" + startDate +", startTime="+ startTime + ", roomName=" + roomName + '}';
    }
}