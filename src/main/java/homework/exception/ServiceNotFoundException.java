package homework.exception;

/**
 * Exception raised when name was not found.
 * 
 * @author Vladimir Pfeffer
 */
public class ServiceNotFoundException extends RuntimeException {
    /**
     * Default constructor.
     */
    public ServiceNotFoundException(){
        super("error.not.found");
    }

    /**
     * Constructor with parameters. Just calls super class.
     *
     * @param message Exception message
     */
    public ServiceNotFoundException(final String message){
        super(message);
    }
}
