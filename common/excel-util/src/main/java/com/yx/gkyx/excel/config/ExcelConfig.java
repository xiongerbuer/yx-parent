package com.yx.gkyx.excel.config;

import com.yx.gkyx.excel.annotation.Table;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class ExcelConfig {

    @Bean
    Map<String, Class<?>> excelModels() {
        Reflections reflections = new Reflections("com.yx.ssyx.excel.model.excel");
        return reflections.getTypesAnnotatedWith(Table.class)
                .stream().collect(Collectors.toMap(
                        clazz -> clazz.getAnnotation(Table.class).name(),
                        Function.identity()
                ));
    }

}
