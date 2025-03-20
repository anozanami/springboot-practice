package me.yeochan.blogdemo.dto;
// 컨트롤러에서 요청한 본문을 받을 객체
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yeochan.blogdemo.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    // toEntity() : 빌더 패턴을 이용해 DTO를 엔티티로 만들어주는 메서드
    // DTO : Article
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
