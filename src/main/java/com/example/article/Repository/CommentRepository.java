package com.example.article.Repository;

import com.example.article.Entity.ArticleEntity;
import com.example.article.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository
        extends JpaRepository<CommentEntity, Integer> {
    //댓글(삽입,삭제,조회)
    //조회(해당하는 게시글에 댓글만 조회)
    //nativeQuery가 false(기본값)이면 SQL는 JSQL형식(Entity이용)
    //@Query(value="SELECT u FROM comment u WHERE u.articleid=:articleid")
    @Query(value = "SELECT * FROM Comment WHERE articleid = :articleid",
    nativeQuery = true) //nativeQuery=정통방식의 SQL문법을 사용할 때
    List<CommentEntity> findByArticleId(Integer articleid);
}
//단위테스트 통해서 필요한 동작을 확인 및 기초데이터 입력