package com.smallflyingleg.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.smallflyingleg.pojo.PageResult;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 用户列表数据
     * @return
     */
    @GetMapping
    public PageResult<SysUser> sysRoleList(Integer pageNumber, Integer pageSize, String q){
        Page<SysUser> sysUsers = userService.selectPage(new Page<>(pageNumber,pageSize),new EntityWrapper<SysUser>().like("user_name",q));
        return new PageResult<SysUser>(sysUsers.getTotal(),sysUsers.getRecords());
    }

    /**
     * 用户添加
     * @param sysUser
     * @return
     */
    @PostMapping
    public boolean add(SysUser sysUser){
        boolean insert = userService.insert(sysUser);
        return insert;
    }

    /**
     * 用户单个修改数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public SysUser get(@PathVariable("id") Integer id){
        SysUser SysUser = userService.selectById(id);
        return SysUser;
    }

    /**
     * 用户修改
     * @param sysUser
     * @return
     */
    @PutMapping
    public boolean edit(SysUser sysUser){
        boolean b = userService.updateById(sysUser);
        return b;
    }

    /**
     * 用户删除
     * @param ids
     * @return
     */
    @DeleteMapping("{ids}")
    public boolean delete(@PathVariable("ids") Long [] ids){
        boolean b = userService.deleteBatchIds(Arrays.asList(ids));
        return b;
    }

}

