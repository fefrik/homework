package homework.service;

import homework.model.TranslationData;
import org.springframework.stereotype.Service;

/**
 * Caesar Service - increase ordinal value of letter about parameter shift
 *
 * @author Vladimir Pfeffer
 */
@Service
public class CaesarService implements Translator {

    @Override
    public String encode(TranslationData data) {
        String text = data.getText();
        String result = "";
        for (Character letter : text.toLowerCase().toCharArray()) {
            int ord = (int)letter + data.getShift();
            result += (char) ord;
        }
        return result;
    }

    @Override
    public String decode(TranslationData data) {
        String text = data.getText();
        String result = "";
        for (Character letter : text.toLowerCase().toCharArray()) {
            int ord = (int)letter - data.getShift();
            result += (char) ord;
        }
        return result;
    }

}
