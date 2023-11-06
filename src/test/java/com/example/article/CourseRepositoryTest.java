package com.example.article;

import com.example.article.Entity.CommentEntity;
import com.example.article.Entity.CourseEntity;
import com.example.article.Repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    //삽입 테스트 겸 기초 데이터 추가
    @Test
    public void registerTest() {
        for (int i=1; i<=50; i++) {
            CourseEntity courseEntity = CourseEntity.builder()
                    .lectureName("삽입테스트" + i)
                    .userID("테스트" + i)
                    .professorName("OO교수" + i)
                    .lecutreYear(2018)
                    .semesterdivide("1학기")
                    .lectureDivide("알고리즘")
                    .evaluationTitle("좋아요" + i)
                    .evaluationContent("테스트 내용" + i)
                    .totalScore("A")
                    .lectureScore("A")
                    .comfortableScore("A")
                    .creditScore("A")
                    .build();

            courseRepository.save(courseEntity);
        }
    }

    //검색
    @Test
    public void searchTest() {
        List<CourseEntity> courseEntities = courseRepository.findByEvaluationID(10);
        System.out.println(courseEntities.toString());
    }
}
