package me.seonwoo.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.seonwoo.springbootdeveloper.domain.Article;
import me.seonwoo.springbootdeveloper.dto.ArticleListViewResponse;
import me.seonwoo.springbootdeveloper.dto.ArticleViewResponse;
import me.seonwoo.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// /articles GET 요청을 처리할 코드
// 블로그 글 전체 리스트 담은 뷰 반환
@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 1. "articles" 키에 블로그 글 리스트저장

        return "articleList"; // 2. articleList.html라는 뷰 조회, response/templates/articleList.html
    }

    // 블로그 글을 반환할 컨트롤러의 메서드
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) { // 1. 인자 id에 URL로 넘어온 값을 받음
        Article article = blogService.findById(id); // 2. findById()로 값을 넘겨 글을 조회
        model.addAttribute("article", new ArticleViewResponse(article)); // 3. 화면에 사용할 모델에 데이터를 저장

        return "article"; // 4. 보여줄 화면의 템플릿 이름을 반환
    }

    @GetMapping("/new-article") // 1. id 키를 가진 쿼리 파라미터 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // 2. id가 없으면 기본 생성자를 이용해 빈 ArticleViewResponse 객체 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // 3. id가 있으면 수정
            Article article = blogService.findById(id); // 4. 기존 값을 가져오는 findByIn() 호출
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }

}
