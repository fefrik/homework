package homework.service;

import homework.model.TranslationData;

/**
 * Translator Interface
 *
 * @author Vladimir Pfeffer
 */
public interface Translator {

    /**
     * Encoding from text to Selected translation method
     * @param text translated text
     * @return
     */
    String encode(TranslationData text);

    /**
     * Decoding from Selected translation methid to Text
     * @param text translated text
     * @return
     */
    String decode(TranslationData text);


}
