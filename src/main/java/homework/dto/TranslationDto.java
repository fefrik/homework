package homework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import homework.StatusMessageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for responses without actual data payload.
 *
 * @author Vladimir Pfeffer
 */
@ApiModel(description="ApiModel.TranslationDto.description")
@JsonPropertyOrder({ "text", "result", "type", "direction"})
public class TranslationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ApiModelProperty.TranslationDto.text.value", required = true, position=1)
    @NotNull
    private final String text;

    @ApiModelProperty(value = "ApiModelProperty.TranslationDto.result.value", required = true, position=2)
    @NotNull
    private final String result;

    @ApiModelProperty(value = "ApiModelProperty.TranslationDto.type.value", required = true, position=3)
    @NotNull
    private final String type;

    @ApiModelProperty(value = "ApiModelProperty.TranslationDto.direction.value", required = true, position=4)
    @NotNull
    private final String direction;

    /**
     * Consturtor
     * @param text
     * @param result
     * @param type
     * @param direction
     */
    public TranslationDto(@NotNull @JsonProperty("text") String text, @NotNull @JsonProperty("result") String result, @NotNull @JsonProperty("type") String type, @NotNull @JsonProperty("direction") String direction) {
        this.text = text;
        this.result = result;
        this.type = type;
        this.direction = direction;
    }

    public String getText() {
        return text;
    }

    public String getResult() {
        return result;
    }

    public String getType() {
        return type;
    }

    public String getDirection() {
        return direction;
    }



}
