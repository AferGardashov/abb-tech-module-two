package jdbc_statements_dbconfig.exeptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
