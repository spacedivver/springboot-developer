package me.seonwoo.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.seonwoo.springbootdeveloper.domain.Article;
import me.seonwoo.springbootdeveloper.dto.AddArticleRequest;
import me.seonwoo.springbootdeveloper.dto.UpdateArticleRequest;
import me.seonwoo.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 서비스 메서드 코드
@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 블로그 글 목록 조회 API
    public List<Article> findAll() { // 데이터베이스에 저장된 글을 모두 가져오는 JPA 지원 메서드 findAll()
        return blogRepository.findAll();
    }

    // 블로그 글 조회 API
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    } // JPA에서 제공하는 findById() 메서드를 사용해 ID를 받아 엔티티를 조회하고
      // 없으면 IllegalArgumentException 예외 발생

    public void delete(long id) { // 블로그 ID를 받아 JPA에서 제공하는 deleteById() 메서드를 이용해 데이터베이스에서 데이터 삭제
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
