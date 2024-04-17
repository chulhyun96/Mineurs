package com.newlecmineursprj.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Qna {
    private Long id;
    private String content;
    private Date regDatetime;
}
