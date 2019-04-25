package com.smallflyingleg.controller;


import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<SysRole> sysRoleList(){
        List<SysRole> sysRoles = sysRoleService.selectList(null);
        return sysRoles;
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
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Long id){
        boolean b = sysRoleService.deleteById(id);
        return b;
    }

}

