package com.mh.blog.service;

import com.mh.blog.entity.Comment;

import java.util.List;

public interface CommentService {
    /*获取评论列表*/
    List<Comment> listCommentByBlogId(Long blogId);
    /*保存评论*/
    Comment saveComment(Comment comment);
}
