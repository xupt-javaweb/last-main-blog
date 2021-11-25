package com.mh.blog.web.admin;

import com.mh.blog.entity.Tag;
import com.mh.blog.service.TargetService;
import com.mh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TargetController {
    @Autowired
    private TargetService targetService;
    @GetMapping("/tags")
    public String list(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                       Pageable pageable, Model model){
        model.addAttribute("page",targetService.listType(pageable));
        return "admin/tags";
    }
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = targetService.getTagByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","该标签名称不能重复添加");
        }
        if (result.hasErrors()){
            return "admin/tags - input";
        }
        Tag t = targetService.saveTags(tag);
        if (t==null){
            attributes.addFlashAttribute("message","操作失败");
        }
        else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return"admin/tags - input";
    }

    @PostMapping("/tags/{id}")
    public String editpost(@Valid Tag tag, BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Tag tag1 = targetService.getTagByName(tag.getName());
        if (tag1!=null){
            result.rejectValue("name","nameError","该标签名称不能重复添加");
        }
        if (result.hasErrors()){
            return "admin/tags - input";
        }
        Tag t = targetService.updateTags(id,tag);
        if (t==null){
            attributes.addFlashAttribute("message","更新失败");
        }
        else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }
    /*编辑标签内容*/
    @GetMapping("/tags/{id}/input")
    public String editorInput(@PathVariable Long id, Model model){
        model.addAttribute(targetService.getTags(id));
        return "admin/tags - input";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        targetService.deleteTags(id);
        attributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/tags";
    }
}
