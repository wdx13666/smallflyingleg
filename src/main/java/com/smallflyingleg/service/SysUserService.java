package com.smallflyingleg.service;

import com.smallflyingleg.pojo.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
public interface SysUserService extends IService<SysUser> {

    public int insertSysUserAndUserRole(SysUser sysUser,Long [] ids);

}
