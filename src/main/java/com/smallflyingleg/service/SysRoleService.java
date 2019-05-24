package com.smallflyingleg.service;

import com.smallflyingleg.pojo.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
public interface SysRoleService extends IService<SysRole> {

    public Boolean insertRoleAndPermission(SysRole sysRole,Long [] ids);

    public List<SysRole> getSysRole(Long id);

    public Boolean updateByRoleId(SysRole sysRole,Long [] ids);

}
