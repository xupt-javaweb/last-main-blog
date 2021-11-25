package com.mh.blog.web;

import com.mh.blog.service.BlogService;
import com.mh.blog.service.TargetService;
import com.mh.blog.service.TypeService;
import com.mh.blog.vo.BlogQuery;
import javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TargetService targetService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) throws NotFoundException {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",targetService.listTagTop(6));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }
    @GetMapping("/aboutme")
    public String aboutme(){
        return "aboutme";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "_fragments :: ";
    }

    @GetMapping("/player")
    public String playMusic(){
        return "player";
    }
    @GetMapping("/tempSearch")
    public String tempSearch(){
        return "tempSearch";
    }
}
