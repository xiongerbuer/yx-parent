package com.yx.ssyx.common.feign;

import com.yx.ssyx.common.vo.CommonResult;
import com.yx.ssyx.common.vo.EnumDescVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "gst-common", contextId = "enum-desc", path = "/enums")
public interface EnumDescFeignClient {

    @GetMapping("/byType")
    CommonResult<List<EnumDescVo>> getByType(@RequestParam("type") String type);

    @GetMapping("/desc")
    CommonResult<EnumDescVo> getByType(@RequestParam("type") String type,
                                       @RequestParam("value") String value);

    @GetMapping("/typeMap")
    CommonResult<Map<String,String>> typeMap(@RequestParam("type") String type);

}
