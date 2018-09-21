package homework.service;

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
    String encode(String text);

    /**
     * Decoding from Selected translation methid to Text
     * @param text translated text
     * @return
     */
    String decode(String text);

}
