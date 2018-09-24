package homework.factory;

import homework.ApplicationTests;
import homework.config.Config;

import homework.model.TranslationData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;


/**
 * Tests for TranslatorServiceImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Import({Config.class})
public class TranslatorServiceImplTest {

    @Autowired
    private TranslatorService translatorService;

    @Mock
    private TranslationData translationData;

    @Test
    public void testMorseEncode() {
        when(translationData.getText()).thenReturn(ApplicationTests.TEST_TEXT);
        String str = translatorService.encode(translationData, TranslatorType.MORSE);
        Assert.assertEquals(ApplicationTests.TEST_TEXT_MORSE_ENCODE, str);
    }

    @Test
    public void testEmptyMorseEncode() {
        when(translationData.getText()).thenReturn(ApplicationTests.EMPTY_TEXT);
        String str = translatorService.encode(translationData, TranslatorType.MORSE);
        Assert.assertEquals(ApplicationTests.EMPTY_TEXT, str);
    }

    @Test
    public void testNullMorseEncode() {
        String str = translatorService.encode(null, TranslatorType.MORSE);
        Assert.assertEquals(ApplicationTests.EMPTY_TEXT, str);
    }

    @Test
    public void testMorseDecode() {
        when(translationData.getText()).thenReturn(ApplicationTests.TEST_TEXT_MORSE_ENCODE);
        String str = translatorService.decode(translationData, TranslatorType.MORSE);
        Assert.assertEquals(ApplicationTests.TEST_TEXT, str);
    }

    @Test
    public void testEmptyMorseDecode() {
        when(translationData.getText()).thenReturn(ApplicationTests.EMPTY_TEXT);
        String str = translatorService.encode(translationData, TranslatorType.MORSE);
        Assert.assertEquals(ApplicationTests.EMPTY_TEXT, str);
    }

    @Test
    public void testNullMorseDecode() {
        String str = translatorService.encode(null, TranslatorType.MORSE);
        Assert.assertEquals(ApplicationTests.EMPTY_TEXT, str);
    }
}
