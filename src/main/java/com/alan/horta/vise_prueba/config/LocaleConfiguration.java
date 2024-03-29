package com.alan.horta.vise_prueba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfiguration {

    @Bean(name = "MensajesServidor")
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("MensajesServidor");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(new Locale("es"));
        return acceptHeaderLocaleResolver;
    }
}
