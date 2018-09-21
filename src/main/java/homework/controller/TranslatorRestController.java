package homework.controller;

import homework.StatusMessageDto;
import homework.dto.TranslationDto;
import homework.exception.ServiceNotFoundException;

import homework.factory.TranslatorService;
import homework.factory.TranslatorType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/translator")
@CrossOrigin(origins = "*")
public class TranslatorRestController {

    private static final String ENCODE = "encode";
    private static final String DECODE = "decode";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TranslatorService translatorService;

    /**
     * Endpoint for execution of translation method
     * @param serviceName Translator Service (morse|plus|etc..)
     * @param action Translator direction (encode|decode)
     * @param text Translated text
     * @return ResponseEntity<TranslationDto>
     * @throws ServiceNotFoundException
     */
    @ApiOperation(value = "Translation", response = TranslationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succefull 200", response = TranslationDto.class),
            @ApiResponse(code = 500, message = "Error 500", response = StatusMessageDto.class),
    })
    @RequestMapping(value = "/{serviceName}/{action}", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity<TranslationDto> translate(
            @ApiParam(value = "Translator Service (morse|plus|etc..)") @PathVariable String serviceName,
            @ApiParam(value = "Translator direction (encode|decode)") @PathVariable String action,
            @ApiParam(value = "Translated text") @RequestBody String json) throws ServiceNotFoundException {
        try {
            JSONObject object = new JSONObject(json);
            String text = object.getString("text");
            String result = "";
            if (ENCODE.equals(action)) {
                logger.info("Encoding string {}", text);
                result = translatorService.encode(text, TranslatorType.valueOf(serviceName.toUpperCase()));
            } else if (DECODE.equals(action)) {
                logger.info("Decoding string {}", text);
                result = translatorService.decode(text, TranslatorType.valueOf(serviceName.toUpperCase()));
            }
            return new ResponseEntity<>(new TranslationDto(text, result, serviceName, action), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            throw new ServiceNotFoundException();
        }
    }


    /**
     * Endpoint for obtaining list of registered translation methods
     */
    @ApiOperation(value = "List of Translator Services", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succefull 200", response = Iterable.class),
            @ApiResponse(code = 500, message = "Error 500", response = StatusMessageDto.class),
    })
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<TranslatorType>> getTranslatorList(){
        logger.info("Get translator List");
        List translatorTypeList = Arrays.asList(TranslatorType.values());
        return new ResponseEntity<List<TranslatorType>>(translatorTypeList, HttpStatus.OK);
    }
}
