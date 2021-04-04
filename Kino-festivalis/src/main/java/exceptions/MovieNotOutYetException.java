package exceptions;

public class MovieNotOutYetException extends Exception {
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public MovieNotOutYetException(String reason) {
        this.reason = reason;
    }
}
