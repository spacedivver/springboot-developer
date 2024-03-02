package me.seonwoo.springbootdeveloper.dto;

import lombok.Getter;
import me.seonwoo.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse { // 응답을 위한 DTO
    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
