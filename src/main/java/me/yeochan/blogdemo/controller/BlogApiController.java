package me.yeochan.blogdemo.controller;

import lombok.RequiredArgsConstructor;
import me.yeochan.blogdemo.domain.Article;
import me.yeochan.blogdemo.dto.AddArticleRequest;
import me.yeochan.blogdemo.dto.ArticleResponse;
import me.yeochan.blogdemo.dto.UpdateArticleRequest;
import me.yeochan.blogdemo.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;
    @PostMapping("api/articles")
    // Request Body로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        // 요청한 자원이 성공적으로 생성, 저장된 블로그 글 정보를 응답 객체에 담아서 전송
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() { // List<DTO>
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity
                .ok()
                .body(articles);
    }

    @GetMapping("api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    } // 왜 ReponseEntity<Article>해서 article을 response을 통해 보내지 않고 <ArticleResponse>를 보내는거지?

    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
