package com.gkyx.user.api;

import com.gkyx.user.service.UserService;
import com.gkyx.vo.user.LeaderAddressVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/leader")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class LeaderAddressApiController {

    private UserService userService;

    //根据userId查询提货点和团长信息
    @GetMapping("/inner/getUserAddressByUserId/{userId}")
    public LeaderAddressVo getUserAddressByUserId(@PathVariable("userId") Long userId) {
        return userService.getLeaderAddressByUserId(userId);
    }
}
