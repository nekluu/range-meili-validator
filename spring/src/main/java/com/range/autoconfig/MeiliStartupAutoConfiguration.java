package com.range.autoconfig;

import com.range.MeiliStartupInitializingBean;
import com.range.meili.http.MeiliHttpClient;
import com.range.meili.validator.MeiliHealthChecker;
import com.range.meili.validator.MeiliIndexChecker;
import com.range.meili.validator.MeiliStartupValidator;
import com.range.meili.validator.MeiliTaskChecker;
import com.range.properties.MeiliStartupProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MeiliStartupProperties.class)
public class MeiliStartupAutoConfiguration {

    @Bean
    public MeiliHttpClient meiliHttpClient() {
        return new MeiliHttpClient();
    }

    @Bean
    public MeiliHealthChecker meiliHealthChecker(MeiliHttpClient httpClient, MeiliStartupProperties properties) {
        return new MeiliHealthChecker(httpClient, properties.getUrl());
    }

    @Bean
    public MeiliTaskChecker meiliTaskChecker(MeiliHttpClient httpClient, MeiliStartupProperties properties) {
        return new MeiliTaskChecker(httpClient, properties.getUrl());
    }

    @Bean
    public MeiliIndexChecker meiliIndexChecker(MeiliHttpClient httpClient, MeiliStartupProperties properties) {
        return new MeiliIndexChecker(httpClient, properties.getUrl());
    }

    @Bean
    public MeiliStartupValidator meiliStartupValidator(
            MeiliHealthChecker healthChecker,
            MeiliTaskChecker taskChecker,
            MeiliIndexChecker indexChecker,
            MeiliStartupProperties properties
    ) {
        return new MeiliStartupValidator(
                healthChecker,
                taskChecker,
                indexChecker,
                properties.getTimeout(),
                properties.getInterval()
        );
    }

    @Bean
    public MeiliStartupInitializingBean meiliStartupInitializingBean(
            MeiliStartupValidator validator
    ) {
        return new MeiliStartupInitializingBean(validator);
    }
}
