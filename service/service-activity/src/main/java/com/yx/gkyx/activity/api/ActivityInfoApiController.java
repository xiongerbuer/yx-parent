package com.yx.gkyx.activity.api;

import com.yx.gkyx.activity.service.ActivityInfoService;
import com.yx.gkyx.activity.service.CouponInfoService;
import com.yx.gkyx.model.activity.CouponInfo;
import com.yx.gkyx.model.order.CartInfo;
import com.yx.gkyx.vo.order.CartInfoVo;
import com.yx.gkyx.vo.order.OrderConfirmVo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/api/activity", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ActivityInfoApiController {

    private ActivityInfoService activityInfoService;

    private CouponInfoService couponInfoService;

    //获取购物车里面满足条件优惠卷和活动的信息
    @PostMapping("inner/findCartActivityAndCoupon/{userId}")
    public OrderConfirmVo findCartActivityAndCoupon(@RequestBody List<CartInfo> cartInfoList,
                                                    @PathVariable("userId") Long userId) {
        return activityInfoService.findCartActivityAndCoupon(cartInfoList, userId);
    }

    @ApiOperation(value = "根据skuId列表获取促销信息")
    @PostMapping("inner/findActivity")
    public Map<Long, List<String>> findActivity(@RequestBody List<Long> skuIdList) {
        return activityInfoService.findActivity(skuIdList);
    }

    @ApiOperation(value = "根据skuID获取营销数据和优惠卷")
    @GetMapping("inner/findActivityAndCoupon/{skuId}/{userId}")
    public Map<String, Object> findActivityAndCoupon(@PathVariable Long skuId,
                                                     @PathVariable Long userId) {
        return activityInfoService.findActivityAndCoupon(skuId, userId);
    }

    //获取购物车对应规则数据
    @ApiOperation(value = "获取购物车对应规则数据")
    @PostMapping("inner/findCartActivityList")
    public List<CartInfoVo> findCartActivityList(@RequestBody List<CartInfo> cartInfoList) {
        return activityInfoService.findCartActivityList(cartInfoList);
    }

    //获取购物车对应优惠卷
    @ApiOperation(value = "获取购物车对应优惠卷")
    @PostMapping("inner/findRangeSkuIdList/{couponId}")
    public CouponInfo findRangeSkuIdList(@RequestBody List<CartInfo> cartInfoList,
                                         @PathVariable("couponId") Long couponId) {
        return couponInfoService.findRangeSkuIdList(cartInfoList, couponId);
    }

    //更新优惠卷使用状态
    @ApiOperation(value = "更新优惠卷使用状态")
    @GetMapping("inner/updateCouponInfoUseStatus/{couponId}/{userId}/{orderId}")
    public Boolean updateCouponInfoUseStatus(@PathVariable("couponId") Long couponId,
                                             @PathVariable("userId") Long userId,
                                             @PathVariable("orderId") Long orderId) {
        couponInfoService.updateCouponInfoUseStatus(couponId, userId, orderId);
        return true;
    }
}
