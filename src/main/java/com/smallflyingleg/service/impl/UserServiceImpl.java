package com.smallflyingleg.service.impl;

import com.smallflyingleg.mapper.UserMapper;
import com.smallflyingleg.pojo.User;
import com.smallflyingleg.service.UserService;
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
