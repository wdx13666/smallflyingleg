package com.smallflyingleg.service;

import com.baomidou.mybatisplus.service.IService;
import com.smallflyingleg.pojo.SysPermission;
import com.smallflyingleg.pojo.SysRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
public interface SysPermissionService extends IService<SysPermission> {

    public List<SysPermission> selectPermissionsByRoleId(Integer roleId);


}
