package homework.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import homework.model.TranslationData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Morse Service
 *
 * @author Vladimir Pfeffer
 */
@Service
public class MorseService implements Translator {

    private static final String SEPARATOR = " | ";

    @Value("classpath:morse.json")
    private Resource res;

    @Override
    public String encode(TranslationData data) {
        String text = data.getText();
        try {
            Map<String, String> morseMap = loadMorseFile();
            String result = "";
            // Iterate over all letters and try to find correct value, then translate and continue to next one.
            for (Character letter : text.toLowerCase().toCharArray()) {
                for (Map.Entry<String, String> entry : morseMap.entrySet()) {
                    if (entry.getKey().equals(letter.toString())) {
                        result = result + entry.getValue() + SEPARATOR;
                        break;
                    }
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decode(TranslationData data) {
        String text = data.getText();
        try {
            Map<String, String> morseMap = loadMorseFile();
            String result = "";

            String[] morseCodes = text.split(SEPARATOR);

            // Iterate over all defined morseCodes and find it in json.
            for (String morseCode : morseCodes) {
                for (Map.Entry<String, String> entry : morseMap.entrySet()) {
                    if (entry.getValue().equals(morseCode)) {
                        result = result + entry.getKey();
                        break;
                    }
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loader from JSON file to Map
     * @return Map with json translation Pairs
     * @throws IOException
     */
    private Map<String, String> loadMorseFile() throws IOException {

        URL url = res.getURL();
        String json = Resources.toString(url, Charsets.UTF_8);

        JSONObject jsonObject = new JSONObject(json);

        Map<String,String> map = new HashMap<>();
        Iterator iter = jsonObject.keys();

        // Iterate over all items of json and create map
        while(iter.hasNext()){
            String key = (String)iter.next();
            String value = jsonObject.getString(key);
            map.put(key,value);
        }
        return map;
    }
}
