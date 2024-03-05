package com.fleetmanagementapi.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties") // Esta anotación especifica el archivo de propiedades 'Email.properties que se utilizara para cargar las funciones del correo electronic.
public class EmailConfig {

    @Value("${email.username}")
    private String email;

    @Value("${email.password}")
    private String password;

    private Properties getMailProperties() { // Este método devuelve un objeto 'properties' que contiene las propiedades de configuration del servidor de correo
        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Ejemplo: smtp.gmail.com
        properties.put("mail.smtp.port", "587"); // ejemplo: 587
        return properties;
    }

    @Bean
    public JavaMailSender javaMailSender(){ // Este método crea y configura un bean 'JavaMailSender' que se utiliza para enviar correos electronics
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(getMailProperties());
        mailSender.setUsername(email);
        mailSender.setPassword(password);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        return mailSender;
    }

    @Bean
    public ResourceLoader resourceLoader(){
        return new DefaultResourceLoader();
    } // Este método crea y configura un bean 'ResourceLoader' que se utiliza para cargar recursos como archivos en el sistema de archivos.

}
