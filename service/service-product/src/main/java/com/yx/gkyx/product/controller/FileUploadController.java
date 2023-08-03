package com.yx.gkyx.product.controller;

import com.yx.gkyx.common.result.Result;
import com.yx.gkyx.product.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件上传接口")
@RestController
@RequestMapping("admin/product")
//@CrossOrigin
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    //图片上传的方法
    @ApiOperation(value = "图片上传")
    @PostMapping("fileUpload")
    public Result<String> fileUpload(MultipartFile file) {
        String url = fileUploadService.uploadFile(file);
        return Result.ok(url);
    }
}
