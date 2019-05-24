package com.smallflyingleg.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.smallflyingleg.pojo.SysRolePermission;
import com.smallflyingleg.pojo.SysUserRole;
import com.smallflyingleg.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("{id}")
    public List<Long> userRoleList(@PathVariable("id") Long id){
        List<SysUserRole> b = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id",id));
        List<Long> collect = b.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return collect;
    }

}

