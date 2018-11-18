package cinema.backend.enums;

/**
 *
 * @author CzP
 */
public enum SeatStatus {
    A("AVAILABLE"),
    O("OCCUPIED");

    private String st;
    
    SeatStatus(String s) {
        this.st = s;
    }
}
