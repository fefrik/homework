package homework.factory;

import homework.model.TranslationData;
import homework.service.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Translator Factory
 */
@Service
public class TranslatorService {

    @Autowired
    private TranslatorFactory translatorFactory;

    public String encode(TranslationData data, TranslatorType type) {
        if (data == null) {
            return "";
        }
        Translator translator = translatorFactory.getTranslator(type);
        return translator.encode(data);
    }


    public String decode(TranslationData data, TranslatorType type) {
        if (data == null) {
            return "";
        }
        Translator translator = translatorFactory.getTranslator(type);
        return translator.decode(data);
    }

}
