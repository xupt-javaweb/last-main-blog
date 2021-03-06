package com.mh.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;

}
