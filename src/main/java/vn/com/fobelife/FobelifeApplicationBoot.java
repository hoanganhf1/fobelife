package vn.com.fobelife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ahuynh 2020-01-01
 */

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class FobelifeApplicationBoot extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FobelifeApplicationBoot.class);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        log.info("****** FOR BE LIFE ******");
        SpringApplication.run(FobelifeApplicationBoot.class, args);
    }

}
