package me.seonwoo.springbootdeveloper.dto;

import lombok.Getter;
import me.seonwoo.springbootdeveloper.domain.Article;

// 뷰에게 데이터를 전달하기 위한 객체 생성
@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
