package com.yejin.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//Entity 클래스와 기본 Repository는 하나의 패키지에서 함께 관리
                                            //상속받을 <Entity 클래스, PK 타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
