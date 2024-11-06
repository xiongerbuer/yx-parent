package com.gkyx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gkyx.enums.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
