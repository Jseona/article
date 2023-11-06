package com.example.article.Repository;

import com.example.article.Entity.OpinionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository
        extends JpaRepository<OpinionEntity, Integer> {
    //기본 CRUD
    //좋아요, 싫어요 추가
    @Modifying  //일부수정
    @Query("UPDATE OpinionEntity u set u.goodcnt=u.goodcnt+1 WHERE u.id=:id")
    void goodcnt(Integer id);

    @Modifying  //일부수정
    @Query("UPDATE OpinionEntity u set u.badcnt=u.badcnt+1 WHERE u.id=:id")
    void badcnt(Integer id);

    //부모테이블에 해당하는 레코드 조회
    @Query(value = "SELECT * FROM opinion WHERE discussionid=:id", nativeQuery = true)
    List<OpinionEntity> findByDiscussionid(Integer id);
}
