package homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for responses without actual data payload.
 *
 * @author Vladimir Pfeffer
 */
@ApiModel(description="ApiModel.TranslationRequestDto.description")
@JsonPropertyOrder({"text", "shift"})
public class TranslationRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("text")
    @ApiModelProperty(value = "ApiModelProperty.TranslationRequestDto.text.value", required = true, position=1)
    @NotNull
    private String text;

    @JsonProperty("shift")
    @ApiModelProperty(value = "ApiModelProperty.TranslationRequestDto.shift.value", position=2)
    private Integer shift = 0;


    public String getText() {
        return text;
    }

    public Integer getShift() {
        return shift;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }
}
