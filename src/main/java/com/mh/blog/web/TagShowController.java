package com.mh.blog.web;

import com.mh.blog.entity.Tag;
import com.mh.blog.entity.Type;
import com.mh.blog.service.BlogService;
import com.mh.blog.service.TargetService;
import com.mh.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TargetService targetService;

    @Autowired
    private BlogService blogService;
    @GetMapping("/tags/{id}")
    public String types(@PageableDefault(size = 8,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        /*拿到分类*/
        /*listTagTop:按照每个标签的博客数目由大到小进行排列*/
        List<Tag> tags = targetService.listTagTop(1000);
        if (id==-1){
            id = tags.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
