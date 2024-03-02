package me.seonwoo.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.seonwoo.springbootdeveloper.domain.Article;
import me.seonwoo.springbootdeveloper.dto.AddArticleRequest;
import me.seonwoo.springbootdeveloper.dto.ArticleResponse;
import me.seonwoo.springbootdeveloper.dto.UpdateArticleRequest;
import me.seonwoo.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 컨트롤러 메서드 코드 작성
@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식을 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    // 전체 글을 조회한 뒤 반환하는 findAllArticles() 메서드
    @GetMapping("/api/articles") // 1. GET 요청이 오면
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll() // 2. 글 전체를 조회하는 findAll() 메서드 호출
                .stream() // 스트림 적용
                .map(ArticleResponse::new) // 3. ArticleResponse로 파싱
                .toList();

        return ResponseEntity.ok()
                .body(articles); // 4. body에 담아 클라이언트에 전송
    }

    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) { // URL에서 {id}에 해당하는 값이 id로 들어옴
        Article article = blogService.findById(id); // @PathVariable은 URL에서 값을 가져오는 애너테이션

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) { // URL에서 {id}에 해당하는 값이 id로 들어옴
        blogService.delete(id); // @PathVariable은 URL에서 값을 가져오는 애너테이션

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}") // 1. PUT 요청이 오면
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) { // 2. Request Body정보가 request로 넘어옴
        Article updatedArticle = blogService.update(id, request); // 3. 서비스 클래스 update() 메서드에 id, request 넘겨줌

        return ResponseEntity.ok()
                .body(updatedArticle); // 4. 응답 값은 body에 담아 전송
    }


}
