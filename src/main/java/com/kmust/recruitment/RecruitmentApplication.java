package com.kmust.recruitment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages={"com.kmust.recruitment.entity"})
public class RecruitmentApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(RecruitmentApplication.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        log.info("Recruitment started at http://localhost:" + serverPort);
    }

    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigFactory factory
                = new MultipartConfigFactory();

        factory.setMaxFileSize(DataSize.ofMegabytes(100));
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));

        MultipartConfigElement element
                = factory.createMultipartConfig();

        return element;
    }
}
