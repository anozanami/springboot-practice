package me.yeochan.blogdemo.repository;

import me.yeochan.blogdemo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
