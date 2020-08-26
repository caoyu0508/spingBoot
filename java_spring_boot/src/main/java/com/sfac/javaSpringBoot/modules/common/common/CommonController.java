package com.sfac.javaSpringBoot.modules.common.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description CommonController
 * @Author HymanHu
 * @Date 2020/8/20 15:33
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private final static Logger LOGGER= LoggerFactory.getLogger(CommonController.class);
    /**
     * 127.0.0.1/common/dashboard ---- get
     */
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "index";
    }

    /**
     * 127.0.0.1/common/403 ---- get
     */
    @GetMapping("/403")
    public String errorPageFor403(){
        return "index";
    }
}
