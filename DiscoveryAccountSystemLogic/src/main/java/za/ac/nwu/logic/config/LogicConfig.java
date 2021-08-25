package za.ac.nwu.logic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import za.ac.nwu.translator.config.TranslatorConfiguration;

@Import({TranslatorConfiguration.class})
@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.logic.flow"
})
public class LogicConfig {
}
