package com.smallflyingleg.controller;


import com.smallflyingleg.pojo.SysRolePermission;
import com.smallflyingleg.service.SysRolePermissionService;
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
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {

    @Autowired
    private SysRolePermissionService rolePermissionService;


    @PostMapping("{id}")
    public boolean save(@RequestBody List<SysRolePermission> rolePermissions, @PathVariable("id") Long id){
        boolean b = rolePermissionService.insertRolePermission(rolePermissions,id);
        return b;
    }

}

