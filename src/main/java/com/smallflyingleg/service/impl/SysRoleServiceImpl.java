package com.smallflyingleg.service.impl;

import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.mapper.SysRoleMapper;
import com.smallflyingleg.pojo.SysRolePermission;
import com.smallflyingleg.service.SysRolePermissionService;
import com.smallflyingleg.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Transactional
    @Override
    public Boolean insertRoleAndPermission(SysRole sysRole, Long[] ids) {
        baseMapper.insert(sysRole);
        rolePermissionService.deleteById(sysRole.getRoleId());
        for (Long id : ids){
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(sysRole.getRoleId());
            rolePermission.setPermissionId(id);
            rolePermissionService.insert(rolePermission);

        }
        return true;
    }

    @Override
    public List<SysRole> getSysRole(Long id) {
        List<SysRole> sysRole = baseMapper.getSysRole(id);
        return sysRole;
    }
}
