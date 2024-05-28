package kg.nurtelecom.internlabs.actionsconfigurator.api.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration of Cross Origin Resource Sharing.
 */
@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {

    /**
     * Adds all url paths to CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**");
    }

}
