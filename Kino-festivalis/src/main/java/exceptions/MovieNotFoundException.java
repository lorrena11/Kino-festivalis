package exceptions;

public class MovieNotFoundException extends Exception {
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public MovieNotFoundException(String reason) {
        this.reason = reason;
    }
}
