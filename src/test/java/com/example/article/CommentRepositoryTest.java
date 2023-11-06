package com.example.article;

import com.example.article.Entity.ArticleEntity;
import com.example.article.Entity.CommentEntity;
import com.example.article.Repository.ArticleRepository;
import com.example.article.Repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class CommentRepositoryTest {
    //댓글 테스트
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void register() { //댓글 등록
        for(int i = 1; i <= 50; i++) {
            ArticleEntity data = ArticleEntity.builder()
                    .id(i)
                    .title("연습")
                    .content("내용")
                    .viewcnt(0)
                    .build();
            CommentEntity commentEntity = CommentEntity.builder()
                .body("댓글입니다."+i)
                .nickname("홍길동")
                .articleEntity(data)
                .build();

            commentRepository.save(commentEntity);
        }
    }
    /*@Test
    public void findByArticleIdTest() { //검색 테스트
        Integer articleId = 3;

        List<CommentEntity> commentEntities=commentRepository.findByArticleId(articleId);
        System.out.println(commentEntities.toString());
    }*/
}
