package com.tobeto.hotel_reservation.core.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfiguration {
    @Bean
    LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.afterPropertiesSet();
        bean.setMessageInterpolator(new CustomMessageInterpolator(bean.getMessageInterpolator()));
        return bean;
    }

}
