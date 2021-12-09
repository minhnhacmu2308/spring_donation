package com.java.spring_donations.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AuthenticationController {

    @GetMapping({"/login"})
    public ModelAndView loadHomePage()
    {
        ModelAndView mv = new ModelAndView("admin/login");
        return mv;
    }
}
