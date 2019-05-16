package com.smallflyingleg.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.smallflyingleg.pojo.PageResult;
import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表数据
     * @return
     */
    @GetMapping
    public PageResult<SysRole> sysRoleList(Integer pageNumber, Integer pageSize,String roleName){
        Page<SysRole> sysRoles = sysRoleService.selectPage(new Page<>(pageNumber,pageSize),new EntityWrapper<SysRole>().like("role_name",roleName));
        return new PageResult<SysRole>(sysRoles.getTotal(),sysRoles.getRecords());
    }

    /**
     * 角色添加
     * @param sysRole
     * @return
     */
    @PostMapping
    public boolean add(SysRole sysRole){
        boolean insert = sysRoleService.insert(sysRole);
        return insert;
    }

    /**
     * 角色单个修改数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public SysRole get(@PathVariable("id") Long id){
        SysRole sysRole = sysRoleService.selectById(id);
        return sysRole;
    }

    /**
     * 角色修改
     * @param sysRole
     * @return
     */
    @PutMapping
    public boolean edit(SysRole sysRole){
        boolean b = sysRoleService.updateById(sysRole);
        return b;
    }

    /**
     * 角色删除
     * @param ids
     * @return
     */
    @DeleteMapping("{ids}")
    public boolean delete(@PathVariable("ids") Long[] ids){
        boolean b = sysRoleService.deleteBatchIds(Arrays.asList(ids));
        return b;
    }

}

