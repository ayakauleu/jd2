package com.yakauleu.ibank.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.yakauleu.ibank.database.util")
@Import(PersistenceConfig.class)
public class TestConfiguration {

}
