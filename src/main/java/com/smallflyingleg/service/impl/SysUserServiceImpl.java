package com.smallflyingleg.service.impl;

import com.smallflyingleg.mapper.SysUserRoleMapper;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.mapper.SysUserMapper;
import com.smallflyingleg.pojo.SysUserRole;
import com.smallflyingleg.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Transactional
    @Override
    public int insertSysUserAndUserRole(SysUser sysUser, Long[] ids) {
        Integer insert = baseMapper.insert(sysUser);
        SysUserRole sysUserRole = new SysUserRole();
        if (ids != null){
            for (Long id : ids){
                sysUserRole.setUserId(sysUser.getUserId());
                sysUserRole.setRoleId(id);
                sysUserRoleMapper.insert(sysUserRole);
            }
        }

        return insert;
    }

    @Override
    public SysUser selectUserAndRoleById(Long id) {
        return baseMapper.selectUserAndRoleById(id);
    }
}
