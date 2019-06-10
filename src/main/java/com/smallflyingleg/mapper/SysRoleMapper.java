package com.smallflyingleg.mapper;

import com.smallflyingleg.pojo.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
    public interface SysRoleMapper extends BaseMapper<SysRole> {

    public List<SysRole> getSysRole(Long id);

    public SysRole selectByRoleId(Long roleId);

}
