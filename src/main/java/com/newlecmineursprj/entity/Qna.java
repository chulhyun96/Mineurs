package com.newlecmineursprj.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Qna {
    private Long id;
    private String content;
    private Date regDatetime;
    private Long views;
    private Long qnaCategory;
    private Long memberId;
}
