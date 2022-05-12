package pl.maciejowsky.banksystem.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//DispatcherServlet context: defines spring MVC infrastrcuture
@Configuration
@ComponentScan("pl.maciejowsky.banksystem")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

}
