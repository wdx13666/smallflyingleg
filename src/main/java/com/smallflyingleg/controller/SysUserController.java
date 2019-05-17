package com.smallflyingleg.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.smallflyingleg.pojo.PageResult;
import com.smallflyingleg.pojo.SysRole;
import com.smallflyingleg.pojo.SysUser;
import com.smallflyingleg.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdx
 * @since 2019-04-22
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /**
     * 用户列表数据
     * @return
     */
    @GetMapping
    public PageResult<SysUser> sysRoleList(Integer pageNumber, Integer pageSize, String userName){
        Page<SysUser> sysUsers = userService.selectPage(new Page<>(pageNumber,pageSize),new EntityWrapper<SysUser>().like("user_name",userName));
        return new PageResult<SysUser>(sysUsers.getTotal(),sysUsers.getRecords());
    }
}

