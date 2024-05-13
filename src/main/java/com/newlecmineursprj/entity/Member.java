package com.newlecmineursprj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
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
    @Builder.Default
    private boolean enabled = true;

    public void setEncodedPassword(String passwordEncode) {
        this.password = passwordEncode;
    }
}
