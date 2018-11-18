package database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "database.util")
@Import(PersistenceConfig.class)
public class TestConfiguration {

}
