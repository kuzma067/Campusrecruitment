package com.kmust.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan(basePackages={"com.kmust.recruitment.entity"})
public class RecruitmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
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
