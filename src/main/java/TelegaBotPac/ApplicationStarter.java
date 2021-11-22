package TelegaBotPac;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@AutoConfigurationPackage
@EnableScheduling
public class ApplicationStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationStarter.class)
                .run(args);
    }
}
