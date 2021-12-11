package com.java.spring_donations.controllers.publics;

import com.java.spring_donations.constants.CommonConstants;
import com.java.spring_donations.domain.User;
import com.java.spring_donations.services.UserService;
import com.java.spring_donations.utils.EncrytedPasswordUtils;
import com.java.spring_donations.utils.MailUtil;
import com.java.spring_donations.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    EncrytedPasswordUtils encrytedPasswordUtils = new EncrytedPasswordUtils();

    @Autowired
    public JavaMailSenderImpl javaMailSenderImpl;

    @GetMapping("/info/{id}")
    public ModelAndView info(@PathVariable int id, HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        boolean auth = Middleware.middleware(request);

        if (auth) {
            User userInformation = userService.getUserById(id);
            mv = new ModelAndView("public/profile");
            mv.addObject("userInformation",userInformation);
        } else {
            mv = new ModelAndView("redirect:/user/login");
        }
        return mv;
    }

    @PostMapping("/update-profile")
    public ModelAndView updateProfile(@ModelAttribute("user") User user, RedirectAttributes rd){
        User userCheck = userService.checkEmailExist(user.getEmail());
        user.setId(userCheck.getId());
        user.setPassword(userCheck.getPassword());
        user.setStatus(userCheck.getStatus());
        String url = "redirect:info/" + userCheck.getId();
        userService.save(user);
        rd.addFlashAttribute(CommonConstants.SUCCESS,
                messageSource.getMessage("update_success", null, Locale.getDefault()));
        ModelAndView mv = new ModelAndView(url);
        return mv;
    }

    @GetMapping("/change-password/{id}")
    public ModelAndView changePassword(@PathVariable int id, HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        boolean auth = Middleware.middleware(request);

        if (auth) {
            User userInformation = userService.getUserById(id);
            mv = new ModelAndView("public/changePassword");
            mv.addObject("userInformation",userInformation);
        } else {
            mv = new ModelAndView("redirect:/user/login");
        }
        return mv;
    }

    @GetMapping("/forgot-password/{id}")
    public ModelAndView forgotPassword(@PathVariable int id, HttpServletRequest request){

            ModelAndView mv = new ModelAndView();
            User userInformation = userService.getUserById(id);
            mv = new ModelAndView("public/postForgotPassword");
            mv.addObject("userInformation",userInformation);

            return mv;
    }

    @PostMapping("/change-password/{id}")
    public ModelAndView changePasswordPost(@PathVariable int id, HttpServletRequest request,
                                           @RequestParam("rePassword") String rePassword,
                                           @RequestParam("password") String password,RedirectAttributes rd){

        String url = "redirect:/user/change-password/" + id;
        System.out.println(rePassword);
        System.out.println(password);
        ModelAndView mv = new ModelAndView();
        boolean auth = Middleware.middleware(request);
        if (auth) {
            User userInformation = userService.getUserById(id);
            if (!password.equalsIgnoreCase(rePassword)) {
                rd.addFlashAttribute(CommonConstants.MSG_REGISTER_ERROR, messageSource.getMessage("password_and_repassword", null, Locale.getDefault()));
                mv = new ModelAndView(url);
            } else {
                String passwordMD5 = encrytedPasswordUtils.md5(password);
                userInformation.setPassword(passwordMD5);
                User userSave = userService.save(userInformation);
                rd.addFlashAttribute(CommonConstants.MSG_REGISTER_SUCCESS, messageSource.getMessage("register_success", null, Locale.getDefault()));
                mv = new ModelAndView(url);
            }
        } else {
            mv = new ModelAndView("redirect:/user/login");
        }
        return mv;
    }

    @PostMapping("/forgot-password/{id}")
    public ModelAndView forgotPasswordPost(@PathVariable int id, HttpServletRequest request,
                                           @RequestParam("rePassword") String rePassword,
                                           @RequestParam("password") String password,RedirectAttributes rd){

        String url = "redirect:/user/forgot-password/" + id;
        System.out.println(rePassword);
        System.out.println(password);
        ModelAndView mv = new ModelAndView();
            User userInformation = userService.getUserById(id);
            if (!password.equalsIgnoreCase(rePassword)) {
                rd.addFlashAttribute(CommonConstants.MSG_REGISTER_ERROR, messageSource.getMessage("password_and_repassword", null, Locale.getDefault()));
                mv = new ModelAndView(url);
            } else {
                String passwordMD5 = encrytedPasswordUtils.md5(password);
                userInformation.setPassword(passwordMD5);
                User userSave = userService.save(userInformation);
                rd.addFlashAttribute(CommonConstants.MSG_REGISTER_SUCCESS, messageSource.getMessage("register_success", null, Locale.getDefault()));
                mv = new ModelAndView(url);
            }
        return mv;
    }

    @GetMapping("/forgot-password")
    public  ModelAndView getFogot(){
        ModelAndView mv = new ModelAndView("public/forgotPassword");
        return mv;
    }

    @PostMapping("/forgot-password")
    public  ModelAndView comfirm(HttpServletRequest request, RedirectAttributes rd){
        String email = request.getParameter("email");
        User user = userService.checkEmailExist(email);
        ModelAndView mv = new ModelAndView();
        if (user != null){
            String link = "http://localhost:8080/user/forgot-password/" + user.getId();
            String html = "<div  class=\"container-fluid\" style=\"text-align: center\">\n" +
                    "    <p style=\"font-size: 20px;font-weight: bold;color: #aaa;margin-top: 10px\">Forgot password</p>\n" +
                    "    <div style=\"width: 600px;height: 400px;border-radius: 5px;\n" +
                    "    box-shadow: rgba(0, 0, 0, 0.4) 0px 0px 10px;margin: 20px auto;padding: 15px\">\n" +
                    "        <p style=\"line-height: 35px;font-size: 16px\">Xin chào, <span style=\"font-weight: bold;color: black\" >"+user.getFullName()+"</span><br>\n" +
                    "        <div style=\"height: 50px ;width: 100px;background-color: #7d90f6;\n" +
                    "            border-radius: 5px;line-height:50px;padding-left:50px;margin: 10px auto;display: flex\">\n" +
                    "            <a href="+link+" style=\"color: white;text-decoration: none\">Here</a>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <p>Contact WorkCV:<br></p>\n" +
                    "            - Phone number:<span style=\"color:#5f80ec\">(024) 6680 5588</span><br>\n" +
                    "            - Email: <a href=\"#\" style=\"color:#5f80ec\"> hotro@workcv.vn</a>\n" +
                    "    </div>\n" +
                    "</div>";

            try {
                MailUtil.sendHtmlMail(this.javaMailSenderImpl,email,"Click button to forgot password",html);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            rd.addFlashAttribute(CommonConstants.CONFIRM_AWAIT,
                    messageSource.getMessage("check_mail", null, Locale.getDefault()));
            mv = new ModelAndView("redirect:/user/forgot-password");
        } else{

            mv = new ModelAndView("redirect:/user/forgot-password");
            rd.addFlashAttribute(CommonConstants.FOGOT_ERROR,
                    messageSource.getMessage("email_not_exited", null, Locale.getDefault()));
        }


        return mv;
    }
}
