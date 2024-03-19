package com.newlecmineursprj.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "member", schema = "mineurs_db")
public class MemberEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "login_id")
    private String loginId;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "reg_date")
    private Date regDate;
    @Basic
    @Column(name = "email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(loginId, that.loginId) && Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(regDate, that.regDate) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, loginId, password, phoneNumber, regDate, email);
    }
}
