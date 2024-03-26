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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member that = (Member) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(loginId, that.loginId) && Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(regDate, that.regDate) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, loginId, password, phoneNumber, regDate, email);
    }
}
