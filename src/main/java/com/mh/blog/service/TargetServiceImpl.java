package com.mh.blog.service;

import com.mh.blog.NotFoundException;
import com.mh.blog.dao.TargetRepository;
import com.mh.blog.entity.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TargetServiceImpl implements TargetService{

    @Autowired
    private TargetRepository targetRepository;
    /*保存标签*/
    @Transactional
    @Override
    public Tag saveTags(Tag tag) {
        return targetRepository.save(tag) ;
    }
    /*查询标签*/
    @Transactional
    @Override
    public Tag getTags(Long id) {
        return targetRepository.findById(id).orElse(null);
    }
    /*分页查询*/
    @Transactional
    @Override
    public Page<Tag> listType(Pageable pageable) {
        return targetRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return targetRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        List<Long>list = convertToList(ids);
        return targetRepository.findAllById(list);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return targetRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    /*更新标签*/
    @Transactional
    @Override
    public Tag updateTags(Long id, Tag tag) {
        /*更新之前先要查询该标签是否存在*/
        Tag tag1 = targetRepository.findById(id).orElse(null);
        if (tag1==null){
            throw new NotFoundException("没有该类型的标签");
        }
        BeanUtils.copyProperties(tag,tag1);
        return targetRepository.save(tag1);
    }

    @Override
    public Tag getTagByName(String name) {
        return targetRepository.findByName(name);
    }

    /*删除标签*/
    @Transactional
    @Override
    public void deleteTags(Long id) {
//        targetRepository.shutDownForeignKeyCheck();
        targetRepository.deleteById(id);
//        targetRepository.openForeignKeyCheck();
    }
}
