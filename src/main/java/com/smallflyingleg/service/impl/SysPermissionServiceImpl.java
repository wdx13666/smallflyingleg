package com.smallflyingleg.service.impl;

import com.smallflyingleg.pojo.SysPermission;
import com.smallflyingleg.mapper.SysPermissionMapper;
import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.service.SysPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> selectPermissionsByRoleId(Integer roleId) {
        List<SysPermission> sysPermissions = baseMapper.selectPermissionsByRoleId(roleId);
        return sysPermissions;
    }


}
