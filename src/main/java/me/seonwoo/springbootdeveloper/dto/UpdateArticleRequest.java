package me.seonwoo.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 블로그 글 수정 요청을 받을 DTO
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateArticleRequest {
    private String title;
    private String content;
}