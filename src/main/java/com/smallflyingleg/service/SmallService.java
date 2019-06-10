package com.smallflyingleg.service;

import com.smallflyingleg.pojo.Small;
import com.baomidou.mybatisplus.service.IService;
import com.smallflyingleg.pojo.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
public interface SmallService extends IService<Small> {

    public Boolean save(Small small, Long userId);
}
