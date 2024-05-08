package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long id;
    private String name;
    private String username;
    private String password;
    private String phoneNumber;
    private Timestamp regDate;
    private String paymentPassword;
    private String email;
    private boolean smsReception;
    private boolean emailReception;
}
