package exercises.test.exeptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
