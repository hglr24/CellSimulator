package Configuration;

/**
 * Create new exception regarding XML configuration
 */
public class XMLException extends RuntimeException{
    /**
     * Throw an XMLException and print cause along with exception in terminal
     * @param cause Error that triggered this exception
     */
    public XMLException(Throwable cause){
        super(cause);
    }

    /**
     *
     * @param errorMessage Error message that you want printed
     */
    public XMLException(String errorMessage){
        super(errorMessage);
    }
}
