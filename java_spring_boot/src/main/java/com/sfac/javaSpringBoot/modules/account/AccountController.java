package com.sfac.javaSpringBoot.modules.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    /**
     * 127.0.0.1/account/users------get
     */
    @GetMapping("/users")
    public String usersPage(){
        return "index";
    }
}
