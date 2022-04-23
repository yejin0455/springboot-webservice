package com.yejin.springboot.domain.user;

import com.yejin.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String pictures;

    @Enumerated(EnumType.STRING)  //데이터베이스로 확인할 때 무슨 의미인지 파악하기 위해 문자열로 저장
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String pictures, Role role) {
        this.name = name;
        this.email = email;
        this.pictures = pictures;
        this.role = role;
    }

    public User update(String name, String pictures) {
        this.name = name;
        this.pictures = pictures;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
