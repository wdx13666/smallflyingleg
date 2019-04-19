package com.smallflyingleg.smallflyingleg.service.impl;

import com.smallflyingleg.smallflyingleg.pojo.User;
import com.smallflyingleg.smallflyingleg.mapper.UserMapper;
import com.smallflyingleg.smallflyingleg.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-04-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
}
