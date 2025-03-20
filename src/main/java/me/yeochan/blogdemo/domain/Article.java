package me.yeochan.blogdemo.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity // 엔터티로 지정
@Getter // getTitle()과 같은 getter들을 애너테이션 하나로 선언 가능
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 선언
public class Article {
    @Id // 기본키를 Id로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1 증가
    @Column(name = "id", updatable = false) // 컬럼의 속성을 할당
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder // 빌더 패턴으로 객체를 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
