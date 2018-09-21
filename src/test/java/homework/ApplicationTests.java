package homework;

import homework.config.Config;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, Config.class})
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void getServices() throws Exception {

		mockMvc.perform(
				get("/")
						.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().is2xxSuccessful());
	}


	@Test
	public void morseEncode() throws Exception {

		mockMvc.perform(
		        post("/translator/morse/encode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{text: ahoj}")).andDo(print()).andExpect(status().is2xxSuccessful());
	}

    @Test
    public void morseDecode() throws Exception {

        mockMvc.perform(
                post("/translator/morse/decode")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{text: .- | .... | --- | .--- |}")).andDo(print()).andExpect(status().is2xxSuccessful());
    }

}