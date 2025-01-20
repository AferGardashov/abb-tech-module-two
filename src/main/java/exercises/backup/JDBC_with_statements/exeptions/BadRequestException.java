package exercises.backup.JDBC_with_statements.exeptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
