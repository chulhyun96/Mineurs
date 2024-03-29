package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long id;
    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private Date regDate;
    private String email;
}
