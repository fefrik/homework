package homework.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for single fieldName error.
 * 
 * @author Vladimir Pfeffer
 */
@ApiModel(description="ApiModel.FieldErrorDto.description")
@JsonPropertyOrder({ "objectName", "fieldName", "message"})
public class FieldErrorDto implements Serializable {
    private static final long serialVersionUID = 8521365548978489489L;

    @ApiModelProperty(value = "ApiModelProperty.FieldErrorDto.objectName.value", required = true, position=10)
    @NotNull
    private final String objectName;

    @ApiModelProperty(value = "ApiModelProperty.FieldErrorDto.fieldName.value", required = true, position=11)
    @NotNull
    private final String fieldName;

    @ApiModelProperty(value = "ApiModelProperty.FieldErrorDto.message.value", required = true, position=12)
    @NotNull
    private final String message;

    /**
     * Constructor.
     *
     * @param objectName Object name having issues
     * @param fieldName Field name having issue
     * @param message Error message related to issue
     */
    @SuppressWarnings("squid:S2637")
    FieldErrorDto(String objectName, String fieldName, String message) {
        this.objectName = objectName;
        this.fieldName = fieldName;
        this.message = message;
    }
    
    /**
     * Gets object name having issue.
     *
     * @return Object name
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Gets fieldName name having issue.
     *
     * @return Field name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets description of the issue.
     *
     * @return Description of the issue
     */
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        return builder.append("FieldErrorDto[objectName=").append(objectName).append(", fieldName=").append(fieldName)
                .append(", message=").append(message).append("]").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldErrorDto that = (FieldErrorDto) o;
        return com.google.common.base.Objects.equal(getObjectName(), that.getObjectName()) &&
                com.google.common.base.Objects.equal(getFieldName(), that.getFieldName()) &&
                com.google.common.base.Objects.equal(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getObjectName(), getFieldName(), getMessage());
    }
}
