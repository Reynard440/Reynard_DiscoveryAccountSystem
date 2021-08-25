package za.ac.nwu.web.sb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import za.ac.nwu.logic.Config.LogicConfig;

//@Import({LogicConfig.class})
@Configuration
@ComponentScan(basePackages = {
        "za.ac.nwu.web.sb.controller",
        "za.ac.nwu.web.sb.exception"
})
public class WebConfig {
}