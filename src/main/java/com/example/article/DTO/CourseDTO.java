package com.example.article.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {
    private int evaluationID;        //평가번호
    private String userID;           //작성자 아이디
    private String lectureName;      //강의명
    private String professorName;    //교수명
    private int lectureYear;         //수강 연도
    private String semesterDivide;   //수강 학기
    private String lectureDivide;    //강의 구분
    private String evaluationTitle;  //평가 제목
    private String evaluationContent;//평가 내용
    private String totalScore;       //종합 점수
    private String creditScore;      //성적 점수
    private String comfortableScore; //널널 점수
    private String lectureScore;     //강의 점수
    private int likeCount;           //추천 갯수
}
