package com.mh.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity/*具备与数据库对应生成表的能力*/
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;/*头像地址*/
    @Temporal(TemporalType.TIMESTAMP)/*在数据库中生成全时间*/
    private Date createTime;
    private boolean adminComment;/*是否是博主*/
    @ManyToOne
    private Blog blog;
    @OneToMany(mappedBy = "parentComment")
    private List<Comment>replyComments = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
}
