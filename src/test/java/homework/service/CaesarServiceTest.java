package homework.service;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Import({Config.class})
public class CaesarServiceTest {

    @Autowired
    private CaesarService caesarService;

    @Mock
    private TranslationData translationData;

    @Test
    public void testEncode() {
        when(translationData.getText()).thenReturn(ApplicationTests.TEST_TEXT);
        when(translationData.getShift()).thenReturn(1);
        Assert.assertEquals(ApplicationTests.TEST_TEXT_CAESAR_ENCODE_1, caesarService.encode(translationData));
    }

    @Test
    public void testDecode() {
        when(translationData.getText()).thenReturn(ApplicationTests.TEST_TEXT_CAESAR_ENCODE_1);
        when(translationData.getShift()).thenReturn(1);
        Assert.assertEquals(ApplicationTests.TEST_TEXT, caesarService.decode(translationData));
    }

}
