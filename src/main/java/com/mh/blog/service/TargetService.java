package com.mh.blog.service;

import com.mh.blog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TargetService {
    /*保存标签*/
    Tag saveTags(Tag tag);
    /*查询标签*/
    Tag getTags(Long id);
    /*更新标签*/
    Tag updateTags(Long id,Tag tag);
    /*根据name查找tag*/
    Tag getTagByName(String name);
    /*删除标签*/
    void deleteTags(Long id);
    /*分页查询*/
    Page<Tag> listType(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);
}
