package com.yx.gkyx.cart.client;

import com.yx.gkyx.model.order.CartInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("service-cart")
public interface CartFeignClient {

    //获取当前用户购物车选中购物项
    /**
     * 根据用户Id 查询购物车列表
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据用户Id 查询购物车列表")
    @GetMapping("/api/cart/inner/getCartCheckedList/{userId}")
    public List<CartInfo> getCartCheckedList(@PathVariable("userId") Long userId);
}
