package com.smallflyingleg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    /**
     * 权限列表
     * @return
     */
    @GetMapping("/permission")
    public String permission(){
        return "permission/permission-list";
    }

}
