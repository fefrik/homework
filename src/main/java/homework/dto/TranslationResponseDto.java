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
@ApiModel(description="ApiModel.TranslationResponseDto.description")
@JsonPropertyOrder({ "text", "result", "type", "direction"})
public class TranslationResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ApiModelProperty.TranslationResponseDto.text.value", required = true, position=1)
    @NotNull
    private String text;

    @ApiModelProperty(value = "ApiModelProperty.TranslationResponseDto.result.value", required = true, position=2)
    @NotNull
    private String result;

    @ApiModelProperty(value = "ApiModelProperty.TranslationResponseDto.type.value", required = true, position=3)
    @NotNull
    private String type;

    @ApiModelProperty(value = "ApiModelProperty.TranslationResponseDto.direction.value", required = true, position=4)
    @NotNull
    private String direction;

    /**
     * Consturtor
     * @param text
     * @param result
     * @param type
     * @param direction
     */
    public TranslationResponseDto(@NotNull @JsonProperty("text") String text, @NotNull @JsonProperty("result") String result, @NotNull @JsonProperty("type") String type, @NotNull @JsonProperty("direction") String direction) {
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

    public void setText(String text) {
        this.text = text;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
