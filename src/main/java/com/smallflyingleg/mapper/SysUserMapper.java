package com.smallflyingleg.mapper;

import com.smallflyingleg.pojo.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    public SysUser selectUserAndRoleById(Long id);

    public Set<SysUser> selectByRoleId(Long roleId);

}
