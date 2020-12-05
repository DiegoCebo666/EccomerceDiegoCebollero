package com.eccomerce.diegocebollero.eccomerce.API;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@ComponentScan("com.eccomerce.diegocebollero.API")
public class WebMvcConfig implements WebMvcConfigurer{
    @Bean
    public LocaleResolver localeResolver(){
        return new CookieLocaleResolver();
    }
    
    @Bean
    public LocaleChangeInterceptor localeInterceptor(){
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
        
    }
 
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(localeInterceptor());
    }


    @Bean(name = "ordersSource")
    public MessageSource getMessageSource(){
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:i18n/orders");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }
}
