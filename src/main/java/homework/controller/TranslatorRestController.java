package homework.controller;

import homework.StatusMessageDto;
import homework.dto.TranslationRequestDto;
import homework.dto.TranslationResponseDto;
import homework.exception.ServiceNotFoundException;

import homework.factory.TranslatorFactory;
import homework.factory.TranslatorType;

import homework.model.TranslationData;
import homework.service.Translator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/translator")
@CrossOrigin(origins = "*")
public class TranslatorRestController {

    private static final String ENCODE = "encode";
    private static final String DECODE = "decode";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TranslatorFactory translatorFactory;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Endpoint for execution of translation method
     * @param serviceName Translator Service (morse|caesar|etc..)
     * @param action Translator direction (encode|decode)
     * @param requestDto Translation Data
     * @return ResponseEntity<TranslationResponseDto>
     * @throws ServiceNotFoundException
     */
    @ApiOperation(value = "Translation", response = TranslationResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succefull 200", response = TranslationResponseDto.class),
            @ApiResponse(code = 500, message = "Error 500", response = StatusMessageDto.class),
    })
    @RequestMapping(value = "/{serviceName}/{action}", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity<TranslationResponseDto> translate(
            @ApiParam(value = "Translator Service (morse|caesar|etc..)") @PathVariable String serviceName,
            @ApiParam(value = "Translator direction (encode|decode)") @PathVariable String action,
            @ApiParam(value = "Translation data") @RequestBody TranslationRequestDto requestDto) throws ServiceNotFoundException {

        TranslatorType serviceType = TranslatorType.valueOf(serviceName.toUpperCase());
        TranslationData data = modelMapper.map(requestDto, TranslationData.class);
        Translator translator = translatorFactory.getTranslator(serviceType);

        String text = data.getText();

        if (ENCODE.equals(action)) {
            logger.info("Encoding string {}", data.getText());
            String result = translator.encode(data);
            return new ResponseEntity<>(new TranslationResponseDto(text, result, serviceName, action), HttpStatus.OK);
        }

        if (DECODE.equals(action)) {
            logger.info("Decoding string {}", data.getText());
            String result = translator.decode(data);
            return new ResponseEntity<>(new TranslationResponseDto(text, result, serviceName, action), HttpStatus.OK);
        }

        throw new ServiceNotFoundException();
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
