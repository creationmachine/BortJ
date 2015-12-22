package com.creationmachine.configuration;

import java.sql.SQLException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ResourceBundleMessageSource;
// import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.view.InternalResourceViewResolver;
// import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.creationmachine")
public class AppConfig
{

//    @Bean
//    public ViewResolver viewResolver(){
//        
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

    // Sets up "main/resources/messages.properties" for locale messages etc
    @Bean
    public MessageSource messageSource(){
        
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    
    @Bean(name = "h2WebServer", initMethod="start", destroyMethod="stop")
    public org.h2.tools.Server h2WebServer() throws SQLException {
        return org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    }


    @Bean(initMethod="start", destroyMethod="stop")
    @DependsOn(value = "h2WebServer")
    public org.h2.tools.Server h2Server() throws SQLException {
        return org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
