package com.example.article;

import com.example.article.Entity.DiscussionEntity;
import com.example.article.Entity.OpinionEntity;
import com.example.article.Repository.OpinionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpinionRepositoryTest {
    @Autowired
    private OpinionRepository opinionRepository;

    @Test
    public void registerTest() {
        for (int i=30; i<=50; i++) {
            DiscussionEntity data = DiscussionEntity.builder()
                    .id(i)
                    /*.subject("테스트 진행" + i)
                    .viewcnt(i)*/
                    .build();

            OpinionEntity opinionEntity = OpinionEntity.builder()
                    .comment("의견입니다." + i)
                    .goodcnt(0)
                    .badcnt(0)
                    .discussionEntity(data)
                    .build();

            opinionRepository.save(opinionEntity);
        }
    }
}
