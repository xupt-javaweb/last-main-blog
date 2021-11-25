package com.mh.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity/*具备与数据库对应生成表的能力*/
@Table(name = "t_blog")
public class Blog implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String firstPicture;/*首图*/
    private String flag;/*标记*/
    private Integer views;/*浏览次数*/
    private boolean appreciation;/*赞赏是否开启*/
    private boolean shareStatement;/*转载声明是否开启*/
    private boolean commentable;/*评论开启*/
    private boolean published;/*是否发布*/
    private boolean recommend;/*是否推荐*/
    private String description;/*文章描述*/
    @Temporal(TemporalType.TIMESTAMP)/*在数据库中生成全时间*/
    private Date createTime;/*创建时间*/
    @Temporal(TemporalType.TIMESTAMP)/*在数据库中生成全时间*/
    private Date updateTime;/*更新时间*/

    @ManyToOne
    private Type type;
    public void getTypes(){
        
        System.out.println(type.getId());
    }

    @ManyToMany(cascade = {CascadeType.PERSIST})/*级联新增，增加博客的同时，也增加标签*/
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment>comments = new ArrayList<>();

    @Transient
    private String tagIds;

    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
