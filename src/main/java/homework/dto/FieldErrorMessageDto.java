package homework.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import homework.StatusMessageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * DTO for responses without actual data payload.
 * 
 * @author Vladimir Pfeffer
 */
@ApiModel(description="ApiModel.FieldErrorMessageDto.description")
@JsonPropertyOrder({ "type", "message", "fieldErrors"})
public class FieldErrorMessageDto extends StatusMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ApiModelProperty.FieldErrorMessageDto.fieldErrors.value", position=5)
    private List<FieldErrorDto> fieldErrors;

    /**
     * Constructor.
     *
     * @param type Status message type
     * @param message Message text
     */
    public FieldErrorMessageDto(StatusMessageType type, String message) {
        super(type, message);
    }

    /**
     * Adds new fieldName error DTO to the status message.
     *
     * @param objectName Object name
     * @param fieldName Field name
     * @param message Error message
     */
    public void add(String objectName, String fieldName, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDto(objectName, fieldName, message));
    }

    /**
     * Gets list of field errors (if any).
     *
     * @return List of field errors or null.
     */
    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldErrorMessageDto that = (FieldErrorMessageDto) o;
        return getType() == that.getType() &&
                Objects.equal(getMessage(), that.getMessage()) &&
                Objects.equal(getFieldErrors(), that.getFieldErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType(), getMessage(), getFieldErrors());
    }
}
