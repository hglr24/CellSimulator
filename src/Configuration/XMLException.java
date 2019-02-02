package Configuration;

public class XMLException extends RuntimeException{
    public XMLException(Throwable cause){
        super(cause);
    }
    public XMLException(String errorMessage){
        super(errorMessage);
    }
}
