package homework.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * Swagger configuration (REST API documentation).
 *
 * @author Vladimir Pfeffer
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.application.version}")
    private String versionNumber;

    @Value("${spring.application.build.timestamp}")
    private String buildTimestamp;

    @Value("${spring.application.build.number}")
    private String buildNumber;

    @Value("${homework.locale}")
    private String localeStr;

    @PostConstruct
    public void init() {
	// Set default locale based on configuration
		Locale.setDefault(Locale.forLanguageTag(localeStr));
    }

    /**
     * Disables external schema validation service to avoid error element
     * in the Swagger-UI, since internal URL cannot be accessed.
     *
     * @return UiConfiguration
     */
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl("")
                .build();
    }

}
