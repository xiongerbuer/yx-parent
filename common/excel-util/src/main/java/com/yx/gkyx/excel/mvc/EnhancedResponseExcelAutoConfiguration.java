package com.yx.gkyx.excel.mvc;

import com.alibaba.excel.converters.Converter;
import com.pig4cloud.plugin.excel.ExcelHandlerConfiguration;
import com.pig4cloud.plugin.excel.aop.DynamicNameAspect;
import com.pig4cloud.plugin.excel.aop.ResponseExcelReturnValueHandler;
import com.pig4cloud.plugin.excel.config.ExcelConfigProperties;
import com.pig4cloud.plugin.excel.processor.NameProcessor;
import com.pig4cloud.plugin.excel.processor.NameSpelExpressionProcessor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(ExcelConfigProperties.class)
public class EnhancedResponseExcelAutoConfiguration {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    @Autowired
    private ResponseExcelReturnValueHandler responseExcelReturnValueHandler;
    @Autowired
    private EnhancedRequestExcelArgumentResolver resolver;

    /**
     * SPEL 解析处理器
     *
     * @return NameProcessor excel名称解析器
     */
    @Bean
    @ConditionalOnMissingBean
    public NameProcessor nameProcessor() {
        return new NameSpelExpressionProcessor();
    }

    /**
     * Excel名称解析处理切面
     *
     * @param nameProcessor SPEL 解析处理器
     * @return DynamicNameAspect
     */
    @Bean
    @ConditionalOnMissingBean
    public DynamicNameAspect dynamicNameAspect(NameProcessor nameProcessor) {
        return new DynamicNameAspect(nameProcessor);
    }

    /**
     * 追加 Excel返回值处理器 到 springmvc 中
     */
    @PostConstruct
    public void setReturnValueHandlers() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
                .getReturnValueHandlers();

        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
        newHandlers.add(responseExcelReturnValueHandler);
        assert returnValueHandlers != null;
        newHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

    /**
     * 追加 Excel 请求处理器 到 springmvc 中
     */
    @PostConstruct
    public void setRequestExcelArgumentResolver() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolverList = new ArrayList<>();
        resolverList.add(resolver);
        assert argumentResolvers != null;
        resolverList.addAll(argumentResolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(resolverList);
    }

    @Configuration
    static class EnhancedExcelHandlerConfiguration extends ExcelHandlerConfiguration {

        @Autowired
        public EnhancedExcelHandlerConfiguration(ExcelConfigProperties configProperties,
                                                 ObjectProvider<List<Converter<?>>> converterProvider) {
            super(configProperties, converterProvider);
        }
    }
}
