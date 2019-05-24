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
     * 角色列表数据 分页
     * @return
     */
    @GetMapping
    public PageResult<SysRole> sysRoleListPage(Integer pageNumber, Integer pageSize,String q){
        Page<SysRole> sysRoles = sysRoleService.selectPage(new Page<>(pageNumber,pageSize),new EntityWrapper<SysRole>().like("role_name",q));
        return new PageResult<SysRole>(sysRoles.getTotal(),sysRoles.getRecords());
    }

    /**
     * 角色列表数据
     * @return
     */
    @GetMapping("/list")
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
    public boolean add(SysRole sysRole,Long [] ids){
        boolean insert = sysRoleService.insertRoleAndPermission(sysRole,ids);
        return insert;
    }

    /**
     * 角色单个修改数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public List<SysRole> get(@PathVariable("id") Long id){
        List<SysRole> sysRole = sysRoleService.getSysRole(id);
        return sysRole;
    }

    /**
     * 角色修改
     * @param sysRole
     * @return
     */
    @PutMapping
    public boolean edit(SysRole sysRole,Long [] ids){
        boolean b = sysRoleService.updateByRoleId(sysRole,ids);
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

