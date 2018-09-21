package homework.factory;

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

    public String encode(String str, TranslatorType type) {
        Translator translator = translatorFactory.getTranslator(type);
        return translator.encode(str);
    }


    public String decode(String str, TranslatorType type) {
        Translator translator = translatorFactory.getTranslator(type);
        return translator.decode(str);
    }

}
