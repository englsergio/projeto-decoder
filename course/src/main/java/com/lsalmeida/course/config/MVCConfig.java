package com.lsalmeida.course.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

//@Configuration
public class MVCConfig {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static LocalDateTimeSerializer LOCAL_DATE_TIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer objectMapperCustomizer() {
        JavaTimeModule module = new JavaTimeModule();
        return builder -> builder
                .modules(module.addSerializer(LOCAL_DATE_TIME_SERIALIZER));
    }

}