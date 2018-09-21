package homework.service;

import org.springframework.stereotype.Service;

/**
 * Simple Plus One Service - increase ordinal value of letter about 1 to forward
 *
 * @author Vladimir Pfeffer
 */
@Service
public class PlusService implements Translator {

    @Override
    public String encode(String text) {
        String result = "";
        for (Character letter : text.toLowerCase().toCharArray()) {
            int ord = (int)letter + 1;
            result += (char) ord;
        }
        return result;
    }

    @Override
    public String decode(String text) {
        String result = "";
        for (Character letter : text.toLowerCase().toCharArray()) {
            int ord = (int)letter - 1;
            result += (char) ord;
        }
        return result;
    }

}
