package com.yakauleu.ibank.web.config;

import com.yakauleu.ibank.database.config.PersistenceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.yakauleu.ibank.web", "com.yakauleu.ibank.service"})
@EnableWebMvc
@Import(value = {InternationalizationConfig.class, PersistenceConfig.class})
public class WebConfiguration {
}
