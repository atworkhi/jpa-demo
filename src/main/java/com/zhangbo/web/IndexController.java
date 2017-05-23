package com.zhangbo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangbo on 2017/5/8.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "sys/index";
    }

}
