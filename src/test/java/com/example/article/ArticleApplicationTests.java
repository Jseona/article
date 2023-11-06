package com.example.article;

import com.example.article.Entity.ArticleEntity;
import com.example.article.Repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleApplicationTests {
    @Autowired
    ArticleRepository articleRepository; //게시글 테스트

    @Test
    public void registerTest() {
        for(int i=1; i<=100; i++) {
            ArticleEntity articleEntity = ArticleEntity.builder()
                    .title("게시글"+i)
                    .content("이건 게시글의 임시 내용입니다."+i)
                    .viewcnt(0)
                    .build();
            articleRepository.save(articleEntity); //JPA 저장
        }
    }

}
