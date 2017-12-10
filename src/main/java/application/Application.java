package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controllers"})
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).logStartupInfo(false).run(args);
    }
}