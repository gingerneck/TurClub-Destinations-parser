package TelegaBotPac;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@AutoConfigurationPackage
public class BotStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BotStarter.class)
                .run(args);
    }
}
