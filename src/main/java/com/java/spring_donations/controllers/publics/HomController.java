package com.java.spring_donations.controllers.publics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomController {

    @GetMapping({"/" , "/index"})
    public ModelAndView loadHomePage()
    {
        ModelAndView mv = new ModelAndView("public/home");
        mv.addObject("activeHome",true);
        return mv;
    }
}
