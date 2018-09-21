package homework.factory;

import homework.service.Translator;

public interface TranslatorFactory {

    Translator getTranslator(TranslatorType parserType);

}
