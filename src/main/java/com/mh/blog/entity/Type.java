package com.mh.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity/*具备与数据库对应生成表的能力*/
@Table(name = "t_type")
public class Type implements Serializable {

    @Id
    @GeneratedValue/*主键的生成策略*/
    private Long id;
    /*后台校验*/
    @NotBlank(message="分类名称不能为空")
    private String name;

    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL)
    private List<Blog> blogs = new ArrayList<>();

}
