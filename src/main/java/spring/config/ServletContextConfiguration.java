package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "spring.controller.web", useDefaultFilters = false, includeFilters = @ComponentScan.Filter(Controller.class))
public class ServletContextConfiguration {

}
