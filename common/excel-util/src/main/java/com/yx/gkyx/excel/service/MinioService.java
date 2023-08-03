package com.yx.gkyx.excel.service;

import com.yx.gkyx.excel.config.MinioConfig;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

@Service
public class MinioService {

    @Autowired
    private final MinioClient minioClient;

    private final MinioConfig.MinioProperties props;

    public MinioService(MinioClient minioClient, MinioConfig.MinioProperties props) {
        this.minioClient = minioClient;
        this.props = props;
    }

    @SneakyThrows
    public InputStream get(Path path) {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket(this.props.getBucket()).
                object(path.toString())
                .build();
        return this.minioClient.getObject(args);
    }

    @SneakyThrows
    public void upload(Path source, InputStream file, Map<String, String> headers) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(this.props.getBucket())
                .object(source.toString())
                .stream(file, file.available(), -1L)
                .headers(headers)
                .build();
        this.minioClient.putObject(args);
    }

    @SneakyThrows
    public void upload(Path source, InputStream file) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(this.props.getBucket())
                .object(source.toString())
                .stream(file, file.available(), -1L)
                .build();
        this.minioClient.putObject(args);
    }

    @SneakyThrows
    public void upload(Path source, InputStream file, String contentType) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(this.props.getBucket())
                .object(source.toString())
                .stream(file, file.available(), -1L)
                .contentType(contentType)
                .build();
        this.minioClient.putObject(args);
    }

}
