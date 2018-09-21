package homework.service;

import homework.config.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Import({Config.class})
public class MorseServiceTest {

    @Autowired
    private MorseService morseService;

    @Test
    public void testEncode() {
        Assert.assertEquals(".- | .... | --- | .--- | ", morseService.encode("ahoj"));
    }

    @Test
    public void testDecode() {
        Assert.assertEquals("ahoj", morseService.decode(".- | .... | --- | .--- | "));
    }

}
