public class XMLException extends RuntimeException{
    public XMLException(String errorMessage, Object errorValues){
        super(String.format(errorMessage, errorValues));
    }

    public XMLException(Throwable cause, String message, Object ... values){
        super(String.format(message, values), cause);
    }

    public XMLException(Throwable cause){
        super(cause);
    }
}
