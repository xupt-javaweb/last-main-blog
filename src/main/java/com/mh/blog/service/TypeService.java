package com.mh.blog.service;

import com.mh.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    /*新增*/
    Type saveType(Type type);
    /*查询*/
    Type getType(Long id);
    /*分页查询*/
    Page<Type> listType(Pageable pageable);

    List<Type> listType();
    /*修改*/
    Type updateType(Long id,Type type);
    /*删除*/
    void deletedType(Long id);

    Type getTypeByName(String name);

    List<Type> listTypeTop(Integer size);
}
