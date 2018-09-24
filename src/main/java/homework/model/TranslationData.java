package homework.model;

import javax.validation.constraints.NotNull;

public class TranslationData {

    @NotNull
    public String text;

    public Integer shift = 1;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }
}
