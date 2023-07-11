package com.atguigu.ssyx.activity.controller;


import com.atguigu.ssyx.activity.service.CouponInfoService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.activity.CouponInfo;
import com.atguigu.ssyx.vo.activity.CouponRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-04-07
 */
@RestController
@RequestMapping(value = "/admin/activity/couponInfo", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
//@CrossOrigin
public class CouponInfoController {

    private CouponInfoService couponInfoService;

    //1 优惠卷分页查询
//    url: `${api_name}/${page}/${limit}`,
//    method: 'get'
    @GetMapping("{page}/{limit}")
    @ApiOperation(value = "优惠卷分页查询")
    public Result<IPage<CouponInfo>> list(@PathVariable Long page,
                                          @PathVariable Long limit) {
        IPage<CouponInfo> pageModel = couponInfoService.selectPageCouponInfo(page, limit);
        return Result.ok(pageModel);
    }

    //2 添加优惠卷
//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @PostMapping("save")
    @ApiOperation(value = "添加优惠卷")
    public Result<String> save(@RequestBody CouponInfo couponInfo) {
        if (couponInfoService.save(couponInfo)) {
            return Result.ok("添加成功。");
        }
        return Result.fail("添加失败！");
    }

    //3 根据id查询优惠卷
//    url: `${api_name}/get/${id}`,
//    method: 'get'
    @GetMapping("get/{id}")
    @ApiOperation(value = "根据id查询优惠卷")
    public Result<CouponInfo> get(@PathVariable Long id) {
        CouponInfo couponInfo = couponInfoService.getCouponInfo(id);
        return Result.ok(couponInfo);
    }

    //4 根据优惠卷id查询规则数据
//    url: `${api_name}/findCouponRuleList/${id}`,
//    method: 'get'
    @GetMapping("findCouponRuleList/{id}")
    @ApiOperation(value = "根据优惠卷id查询规则数据")
    public Result<Map<String, Object>> findCouponRuleList(@PathVariable Long id) {
        Map<String, Object> map = couponInfoService.findCouponRuleList(id);
        return Result.ok(map);
    }

    //5 添加优惠卷规则数据
//    url: `${api_name}/saveCouponRule`,
//    method: 'post',
//    data: rule
    @PostMapping("saveCouponRule")
    @ApiOperation(value = "添加优惠卷规则数据")
    public Result<Boolean> saveCouponRule(@RequestBody CouponRuleVo couponRuleVo) {
        couponInfoService.saveCouponRule(couponRuleVo);
        return Result.ok(true);
    }
}

