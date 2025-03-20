package me.yeochan.blogdemo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.yeochan.blogdemo.domain.Article;
import me.yeochan.blogdemo.dto.AddArticleRequest;
import me.yeochan.blogdemo.dto.UpdateArticleRequest;
import me.yeochan.blogdemo.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록, 사실 이게 무슨 말인지 잘 모르겠음 -> 해당 클래스(BlogService)를 빈으로 서블릿 컨테이너에 등록한다.

public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
        // save(): 부모 클래스에 이미 선언된 메서드, AddArticleRequest 클래스에 저장된 데이터를 article 데이터베이스에 저장한다.
    }

    // DB에 저장되어 있는 글을 모두 가져오는 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void deleteById(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id)); // 이 예외처리는 왜 하는거지?

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
