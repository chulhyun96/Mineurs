package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    private Long id;
    private String content;
    private Date regDatetime;
    private Long views;
    private Long categoryId;
    private Long memberId;
    private Long boardId;
    private String summary;
    private String categoryName;
    private String password;

}
