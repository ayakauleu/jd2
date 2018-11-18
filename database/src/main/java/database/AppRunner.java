package database;

import database.config.PersistenceConfig;
import database.model.Payment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);

        System.out.println();
    }

    private static void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Object dataSource = context.getBean("dataSource");
        System.out.println();
    }
}
