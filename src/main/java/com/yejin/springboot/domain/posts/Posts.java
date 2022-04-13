package com.yejin.springboot.domain.posts;

import com.yejin.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter  //클래스 내 모든 필터의 Getter 메소드를 자동생성 (Lombok)
@NoArgsConstructor  //파라미터가 없는 기본 생성자 자동 추가 (Lombok)
@Entity  //DB 테이블과 링크될 클래스 (JPA)
public class Posts extends BaseTimeEntity {

    @Id  //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //PK 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)  //기본값 외에 추가로 변경이 필요할 경우 선언, 해당 클래스의 필드는 모두 칼럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder  //해당 클래스의 빌더 패턴 클래스 생성 (Lombok)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
