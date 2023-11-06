package com.example.article.Repository;

import com.example.article.Entity.DiscussionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository
        extends JpaRepository<DiscussionEntity, Integer> {
    //기본 CRUD
    //조회수 추가
    @Modifying  //일부수정
    @Query(value = "UPDATE discussion u set u.viewcnt=u.viewcnt+1 WHERE u.id=:id"
    , nativeQuery = true)
    void viewcnt(@Param("id") Integer id);
}
