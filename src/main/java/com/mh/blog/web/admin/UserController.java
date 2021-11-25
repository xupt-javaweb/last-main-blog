package com.mh.blog.web.admin;

import com.mh.blog.entity.Type;
import com.mh.blog.entity.User;
import com.mh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String list(@PageableDefault(size=4,sort = {"id"},direction = Sort.Direction.DESC)
                       Pageable pageable, Model model){
        model.addAttribute("page",userService.listUser(pageable));
        return"admin/user";
    }

    /*编辑分类*/
    /*通过id拿到要编辑的内容，跳转到types - input页面上，并且通过model.addAttribute方法填充分类的名称*/
    @GetMapping("/user/{id}/input")
    public String editorInput(@PathVariable Long id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "admin/user - input";
    }

    /*更新分类*/
    @PostMapping("/user/{id}")
    public String editPost(@Valid User user, BindingResult result, @PathVariable Long id,
                           RedirectAttributes attributes,
                           @RequestParam String username,
                           @RequestParam String email){
        User user1 = userService.checkEditUser(username,email);
        if (user1!=null){
            result.rejectValue("username","usernameError","用户名或邮箱已被注册！");
        }
        if (result.hasErrors()){
            return "admin/user - input";
        }
        User u = userService.updateUser(id,user);
        if (u==null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        userService.deletedUser(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/user";
    }
}
