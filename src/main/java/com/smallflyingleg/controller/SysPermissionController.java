package com.smallflyingleg.controller;


import com.smallflyingleg.pojo.SysPermission;
import com.smallflyingleg.service.SysPermissionService;
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
@RequestMapping("/sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 根据角色id查询资源
     * @param roleId
     * @return
     */
    @GetMapping("role/{roleId}")
    public List<SysPermission> findByRoleId(@PathVariable("roleId") Integer roleId){
        List<SysPermission> sysPermissions = permissionService.selectPermissionsByRoleId(roleId);
        return sysPermissions;
    }

    /**
     * 资源列表数据
     * @return
     */
    @GetMapping
    public List<SysPermission> sysRoleList(){
        List<SysPermission> sysPermissions = permissionService.selectList(null);
        return sysPermissions;
    }

    /**
     * 资源添加
     * @param sysPermission
     * @return
     */
    @PostMapping
    public boolean add(SysPermission sysPermission){
        boolean insert = permissionService.insert(sysPermission);
        return insert;
    }

    /**
     * 资源单个修改数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public SysPermission get(@PathVariable("id") Integer id){
        SysPermission SysPermission = permissionService.selectById(id);
        return SysPermission;
    }

    /**
     * 资源修改
     * @param sysPermission
     * @return
     */
    @PutMapping
    public boolean edit(SysPermission sysPermission){
        boolean b = permissionService.updateById(sysPermission);
        return b;
    }

    /**
     * 资源删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Integer id){
        boolean b = permissionService.deleteById(id);
        return b;
    }

}

