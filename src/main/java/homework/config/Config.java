package homework.config;

import homework.factory.TranslatorFactory;
import homework.service.MorseService;
import homework.service.PlusService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.*;

/**
 *  Register of translation method
 */
@Configuration
@ComponentScan(basePackages = {"homework.factory", "homework.service"})
public class Config {

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
    PlusService plusService;

    /**
     * Registration of Plus One Service
     * @return PlusService
     */
    @Bean(name = "plus")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PlusService plusService() {
        return plusService;
    }

}
