package homework.factory;

import homework.config.Config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Tests for TranslatorServiceImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Import({Config.class})
public class TranslatorServiceImplTest {

    @Autowired
    private TranslatorService translatorService;

    @Test
    public void testMorseEncode() {
        String str = translatorService.encode("ahoj", TranslatorType.MORSE);
        Assert.assertEquals(".- | .... | --- | .--- | ", str);
    }

    @Test
    public void testEmptyMorseEncode() {
        String str = translatorService.encode("", TranslatorType.MORSE);
        Assert.assertEquals("", str);
    }

    @Test
    public void testNullMorseEncode() {
        String str = translatorService.encode(null, TranslatorType.MORSE);
        Assert.assertEquals("", str);
    }

    @Test
    public void testMorseDecode() {
        String str = translatorService.decode(".- | .... | --- | .--- | ", TranslatorType.MORSE);
        Assert.assertEquals("ahoj", str);
    }

    @Test
    public void testEmptyMorseDecode() {
        String str = translatorService.encode("", TranslatorType.MORSE);
        Assert.assertEquals("", str);
    }

    @Test
    public void testNullMorseDecode() {
        String str = translatorService.encode(null, TranslatorType.MORSE);
        Assert.assertEquals("", str);
    }
}
