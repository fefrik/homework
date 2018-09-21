package homework.dto;

import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import homework.StatusMessageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO for responses without actual data payload.
 *
 * @author Vladimir Pfeffer
 */
@ApiModel(description="ApiModel.StatusMessageDto.description")
@JsonPropertyOrder({ "type", "message"})
public class StatusMessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ApiModelProperty.StatusMessageDto.type.value", required = true, position=1)
    @NotNull
    private final StatusMessageType type;

    @ApiModelProperty(value = "ApiModelProperty.StatusMessageDto.message.value", required = true, position=2)
    @NotNull
    private final String message;

    /**
     * Constructor.
     *
     * @param type Status message type
     * @param message Message text
     */
    @SuppressWarnings("squid:S2637")
    @JsonCreator
    public StatusMessageDto(@JsonProperty("type") StatusMessageType type, @JsonProperty("message") String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Gets message.
     * @return Message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets status message type.
     *
     * @return Status message type.
     */
    public StatusMessageType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusMessageDto that = (StatusMessageDto) o;
        return getType() == that.getType() &&
                Objects.equal(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getType(), getMessage());
    }
}
