package com.tjc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: TJC
 * @Date: 2020/6/16 13:02
 * @description: TODO
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/qq")
    public String toQQ() {
        return "redirect:/";
    }

    @RequestMapping("/weibo")
    public String toWeibo() {
        return "redirect:/";
    }
}
