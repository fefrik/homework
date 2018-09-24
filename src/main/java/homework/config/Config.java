package homework.config;

import homework.factory.TranslatorFactory;
import homework.service.MorseService;
import homework.service.CaesarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  Register of translation method
 */
@Configuration
@ComponentScan(basePackages = {"homework.factory", "homework.service"})
public class Config {

    /**
     * Model Mapper
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Factory Bean Locator
     * @return
     */
    @Bean
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(TranslatorFactory.class);
        return factoryBean;
    }

    @Autowired
    MorseService morseService;

    /**
     * Registration of Morse Service
     * @return MorseService
     */
    @Bean(name = "morse")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MorseService morseService() {
        return morseService;
    }


    @Autowired
    CaesarService caesarService;

    /**
     * Registration of Caesar Service
     * @return CaesarService
     */
    @Bean(name = "caesar")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CaesarService caesarService() {
        return caesarService;
    }

}
