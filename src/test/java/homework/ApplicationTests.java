package homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import homework.config.Config;
import homework.dto.TranslationRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {Application.class, Config.class})
public class ApplicationTests {

	public static final String TEST_TEXT_MORSE_ENCODE = ".- | .... | --- | .--- | ";
	public static final String TEST_TEXT_CAESAR_ENCODE_1 = "bipk";
	public static final String TEST_TEXT_CAESAR_ENCODE_2 = "cjql";
	public static final String TEST_TEXT_CAESAR_ENCODE_3 = "dkrm";
	public static final String TEST_TEXT = "ahoj";
	public static final String EMPTY_TEXT = "";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getServices() throws Exception {

		mockMvc.perform(
				get("/")
						.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is2xxSuccessful());
	}


	@Test
	public void morseEncode() throws Exception {

        TranslationRequestDto data = new TranslationRequestDto();
        data.setText(TEST_TEXT);

        String jsonInString = objectMapper.writeValueAsString(data);

		mockMvc.perform(
		        post("/translator/morse/encode")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonInString)
                        ).andDo(print()).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void morseDecode() throws Exception {

		TranslationRequestDto data = new TranslationRequestDto();
		data.setText(TEST_TEXT_MORSE_ENCODE);

		String jsonInString = objectMapper.writeValueAsString(data);

		mockMvc.perform(
				post("/translator/morse/decode")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonInString)
		).andDo(print()).andExpect(status().is2xxSuccessful());
	}

}