package com.example.article;

import com.example.article.Entity.DiscussionEntity;
import com.example.article.Repository.DiscussionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscussionRepositoryTest {
    @Autowired
    private DiscussionRepository discussionRepository;

    //토론주제 등록, 삽입, 수정, 삭제 등 기본 기능은 단위테스트가 필요 없음.
    //여기서는 기초데이터 생성을 위해 테스트 진행
   @Test
    public void register() {
       for (int i=1; i<=50; i++) {
           DiscussionEntity data = DiscussionEntity.builder()
                   .subject("테스트 진행" + i)
                   .viewcnt(i)
                   .build();

           discussionRepository.save(data);
       }
    }

    //조회수 테스트
    @Test
    public void viewcntTest() {
       discussionRepository.viewcnt(2);
    }
}
