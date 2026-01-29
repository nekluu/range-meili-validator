package com.range;

import com.range.meili.validator.MeiliStartupValidator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MeiliStartupInitializingBean implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final MeiliStartupValidator validator;

    public MeiliStartupInitializingBean(MeiliStartupValidator validator) {
        this.validator = validator;
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        validator.validate();
    }
}
