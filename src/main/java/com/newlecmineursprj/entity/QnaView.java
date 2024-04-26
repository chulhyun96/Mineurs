package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaView {

    private Long id;
    private String mainImgPath;
    private String summary;
    private String userName;
    private Date regDateTime;
    private Long views;
}