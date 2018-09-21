package homework.error;

import homework.StatusMessageDto;
import homework.StatusMessageType;
import homework.dto.FieldErrorMessageDto;
import homework.exception.ServiceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;

/**
 * Exception handler to create response messages for different exceptions.
 * 
 * @author Vladimir Pfeffer
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @Autowired
    protected MessageSource translator;

    /**
     * Constructor.
     */
    public RestResponseEntityExceptionHandler(){
        super();
    }

    /**
     * Handle not found exceptions.
     *
     * @param ex Runtime Exception
     * @param request Web request
     * @return Response Entity with error description.
     */
    @ExceptionHandler({ServiceNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request){
        String message = translator.getMessage(ex.getMessage(), null, ex.getMessage(), null);
        final StatusMessageDto body = new StatusMessageDto(StatusMessageType.WARNING, message);
        return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handle NestedRuntimeException.
     *
     * @param ex Runtime Exception
     * @param request Web request
     * @return Response Entity with error description.
     */
    @ExceptionHandler({NestedRuntimeException.class})
    public ResponseEntity<Object> handleNestedRuntimeException(final RuntimeException ex, final WebRequest request){
        log.error("handleNestedRuntimeException()", ex);
        String exMsg = getRootCause(ex).getMessage();
        if (null == exMsg || exMsg.isEmpty()) {
            exMsg = ex.getMessage();
        }
        final StatusMessageDto body = new StatusMessageDto(StatusMessageType.ERROR, exMsg);
        return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleMethodArgumentNotValid()", ex);
        String message = translator.getMessage("error.methodArgumentNotValid", null, "error.methodArgumentNotValid", null);
        FieldErrorMessageDto dto = new FieldErrorMessageDto(StatusMessageType.ERROR, message);
        processFieldErrors(dto, ex.getBindingResult().getFieldErrors());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        // We will always add body to error responses if not already added
        // and log exception details.
        Object exBody = body;
        if(null == exBody){
            log.error("handleExceptionInternal()", ex);
            String message = translator.getMessage(ex.getMessage(), null, ex.getMessage(), null);
            exBody = new StatusMessageDto(StatusMessageType.ERROR, message);
        }
        return super.handleExceptionInternal(ex, exBody, headers, status, request);
    }
    
    /**
     * Adds field errors to DTO.
     * 
     * @param dto DTO to add errors to
     * @param fieldErrors List of field errors
     */    
    private void processFieldErrors(FieldErrorMessageDto dto, List<FieldError> fieldErrors) {      
        fieldErrors.forEach(fieldError -> {
            String message = translator.getMessage(fieldError.getDefaultMessage(), 
                    null, fieldError.getDefaultMessage(), null);
            dto.add(fieldError.getObjectName(), fieldError.getField(),
                    message);
        });
    }
    
    /**
     * Finds root cause of exception.
     * 
     * @param t Throwable
     * @return Root cause (Throwable)
     */
    private Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;
        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

}
