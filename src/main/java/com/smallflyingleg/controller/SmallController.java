package com.smallflyingleg.controller;


import com.smallflyingleg.pojo.Small;
import com.smallflyingleg.service.SmallService;
import com.smallflyingleg.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wdx
 * @since 2019-06-06
 */
@RestController
@RequestMapping("/small")
public class SmallController {

    @Autowired
    private SmallService smallService;
    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public Boolean saveSmall(Small small,Long userId){
        Boolean save = smallService.save(small, userId);
        return save;
    }

}

