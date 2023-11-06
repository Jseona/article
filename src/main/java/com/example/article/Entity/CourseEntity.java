package com.example.article.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Evaluation")
@SequenceGenerator(
        name="Evaluation_SEQ",
        sequenceName = "Evaluation_SEQ",
        initialValue =1,
        allocationSize=1) //순차번호 생성
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Evaluation_SEQ")
    private int evaluationID;        //평가번호
    @Column(length = 50)
    private String userID;           //작성자 아이디
    @Column(length = 50)
    private String lectureName;      //강의명
    @Column(length = 50)
    private String professorName;    //교수명
    @Column
    private int lecutreYear;         //수강 연도
    @Column(length = 20)
    private String semesterdivide;   //수강 학기
    @Column(length = 10)
    private String lectureDivide;    //강의 구분
    @Column(length = 50)
    private String evaluationTitle;  //평가 제목
    @Lob
    @Column
    private String evaluationContent;//평가 내용
    @Column(length = 10)
    private String totalScore;       //종합 점수
    @Column(length = 10)
    private String creditScore;      //성적 점수
    @Column(length = 10)
    private String comfortableScore; //널널 점수
    @Column(length = 10)
    private String lectureScore;     //강의 점수
    @Column
    private int likeCount;           //추천 갯수
}
