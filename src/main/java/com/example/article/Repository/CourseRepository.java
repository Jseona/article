package com.example.article.Repository;

import com.example.article.Entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository
extends JpaRepository<CourseEntity,Integer> {
    //추천 개수 증가
    @Modifying
    @Query("UPDATE CourseEntity u set u.likeCount=u.likeCount+1 where u.evaluationID=:evaluationID")
    void likecnt(Integer evaluationID);

    //ID로 검색
    @Query(value = "SELECT * FROM evaluation WHERE evaluationID =:evaluationID",
            nativeQuery = true)
    //nativeQuery=정통방식의 SQL문법을 사용할 때
    List<CourseEntity> findByEvaluationID(Integer evaluationID);

    //제목으로 검색시 페이지처리
    @Query("SELECT u FROM CourseEntity u WHERE u.evaluationTitle like %:keyword%")
    Page<CourseEntity> searchTitle(String keyword, Pageable pageable);

    //작성자으로 검색시 페이지처리
    @Query("SELECT u FROM CourseEntity u WHERE u.userID like %:keyword%")
    Page<CourseEntity> searchWriter(String keyword, Pageable pageable);

    //제목+작성자으로 검색시 페이지처리
    @Query("SELECT u FROM CourseEntity u WHERE u.evaluationTitle like %:keyword% or u.userID like %:keyword% ")
    Page<CourseEntity> searchTitleContent(String keyword, Pageable pageable);
}
