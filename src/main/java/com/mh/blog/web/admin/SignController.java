package com.mh.blog.web.admin;

import com.mh.blog.entity.User;
import com.mh.blog.service.UserService;
import com.mh.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class SignController {
    @Autowired
    private UserService userService;
//    张嘉祺nb
    @PostMapping("/signin")
    public String signin(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String nickname,
                         RedirectAttributes attributes,
                         Model model){
        User user1 = userService.checkSignUser(username,nickname);
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(MD5Utils.code(password));
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        if (user1!=null){
            model.addAttribute("message","该用户名或昵称已被注册,请重新输入！");
            return"admin/login";
        }else{
            userService.save(user);
            attributes.addFlashAttribute("message","注册成功，请登录！");
        }
        return "redirect:/admin";

    }
    @GetMapping("/signin")
    public String signinPage1(){
        return"admin/signin";
    }
}
