package com.yx.ssyx.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.yx.ssyx.common.constant.DateTimeConstants;
import com.yx.ssyx.common.feign.EnumDescFeignClient;
import com.yx.ssyx.common.http.mvc.CurrentUserHandlerMethodArgumentResolver;
import com.yx.ssyx.common.http.mvc.GlobalResponseHandler;
import com.yx.ssyx.common.http.mvc.RequestUserContextAdvice;
import com.yx.ssyx.common.http.serializer.JsonDecimalFieldSerializer;
import com.yx.ssyx.common.vo.CommonResult;
import com.yx.ssyx.common.vo.EnumDescVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

@Configuration
@AutoConfiguration(before = WebMvcAutoConfiguration.class)
@ComponentScan(basePackageClasses = {GlobalResponseHandler.class})
public class HttpConfig {

    @Bean
    public FeignContextInterceptor feignContextInterceptor() {
        return new FeignContextInterceptor();
    }

    @Bean
    @Order(0)
    public CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver() {
        return new CurrentUserHandlerMethodArgumentResolver();
    }

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return jacksonObjectMapperBuilder -> {
//            jacksonObjectMapperBuilder.featuresToDisable(MapperFeature.ALLOW_COERCION_OF_SCALARS);
            jacksonObjectMapperBuilder.featuresToDisable(FAIL_ON_EMPTY_BEANS);
            jacksonObjectMapperBuilder.featuresToEnable(WRITE_BIGDECIMAL_AS_PLAIN);
//            jacksonObjectMapperBuilder.featuresToDisable(UNTYPED_SCALARS);
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class,
                    new LocalDateTimeSerializer(DateTimeConstants.DATE_TIME));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class,
                    new LocalDateTimeDeserializer(DateTimeConstants.DATE_TIME));
            // 把所有long类型转成string类型，防止前端number因为浮点型精度超过52位而丢失精度
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
//            jacksonObjectMapperBuilder.serializerByType(Enum.class, globalEnumSerializer);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
//            jacksonObjectMapperBuilder.deserializerByType(Long.class, NumberDeserializers.NumberDeserializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, new JsonDecimalFieldSerializer());
//            jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, NumberSerializer.bigDecimalAsStringSerializer());
            jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            jacksonObjectMapperBuilder.modulesToInstall(JavaTimeModule.class);
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
        return (pageableResolver) -> {
            pageableResolver.setOneIndexedParameters(true);
            pageableResolver.setMaxPageSize(500);
            pageableResolver.setPageParameterName("pageNumber");
            pageableResolver.setSizeParameterName("pageSize");
        };
    }

    //    @Bean
    public StdSerializer<Enum> globalEnumSerializer(EnumDescFeignClient enumDescFeignClient) {
        return new StdSerializer<Enum>(Enum.class) {
            @Override
            public void serialize(Enum anEnum, JsonGenerator gen, SerializerProvider serializerProvider) throws
                    IOException {
                CommonResult<EnumDescVo> result = enumDescFeignClient.getByType(anEnum.getDeclaringClass().getCanonicalName(), anEnum.name());
                if (result == null || result.getData() == null) {
                    gen.writeString(anEnum.name());
                } else {
                    gen.writeObject(result.getData());
                }
            }
        };
    }

    @Configuration
    @AutoConfiguration(before = WebMvcAutoConfiguration.class,
            beforeName = "org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration")
    @AllArgsConstructor(onConstructor_ = @Autowired)
    public static class MvcConfig implements WebMvcConfigurer {

        CurrentUserHandlerMethodArgumentResolver methodArgumentResolver;
        RequestUserContextAdvice interceptor;

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            resolvers.add(methodArgumentResolver);
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(interceptor);
        }
    }

    @Configuration
    @ConditionalOnClass(name = "org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer")
    public static class RestConfig {

        @Bean
        public RepositoryRestConfigurer restConfigurer() {
            return new RepositoryRestConfigurer() {
                @Override
                public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
                    config.setMaxPageSize(1000)
                            .setPageParamName("pageNumber")
                            .setLimitParamName("pageSize")
                            .setDefaultMediaType(MediaType.APPLICATION_JSON)
                            .useHalAsDefaultJsonMediaType(false)
                            .setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED)
                            .setExposeRepositoryMethodsByDefault(false);
                }
            };
        }
    }

}
