package com.yx.ssyx.excel.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperties props) {
        return MinioClient.builder()
                .endpoint(props.url)
                .credentials(props.accessKey, props.secretKey)
                .region(props.region)
                .build();
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "spring.minio")
    public static class MinioProperties {
        String url;
        String bucket;
        String accessKey;
        String secretKey;
        String region;
    }

}
