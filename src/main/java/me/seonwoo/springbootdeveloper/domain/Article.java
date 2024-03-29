package me.seonwoo.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity // 엔티티로 지정
public class Article {

    @Id // id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name="id",updatable = false)
    private Long id;

    @Column(name="title",nullable = false) // 'title'이라는 not null 컬럼과 매핑
    private String title;

    @Column(name="content",nullable = false)
    private String content;

    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }

    // 엔티티에 요청받은 내용으로 값을 수정하는 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 엔티티 생성 시간, 수정 시간 추가, 글이 언제 생성되었는지
    @CreatedDate // 엔티티가 생성될 때 생성 시간 저장
    @Column(name = "created_at") // 생성 시간을 created_at 컬럼에 저장
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될 때 수정 시간 저장
    @Column(name = "updated_at") // 수정되 시간을 updated_at 컬럼에 저장
    private LocalDateTime updatedAt;
}
