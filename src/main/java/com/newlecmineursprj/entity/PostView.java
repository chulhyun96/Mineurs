package com.newlecmineursprj.entity;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostView {

    private String boardName;
    private String summary;
    private String content;
    private boolean state;
    private String memberName;
    private Date regDateTime;
    private Long id;
    private Long memberId;
}
