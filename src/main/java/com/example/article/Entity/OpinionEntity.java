package com.example.article.Entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="opinion")
@SequenceGenerator(name="opinion_SEQ", sequenceName = "opinion_SEQ",
        initialValue = 1, allocationSize = 1)
public class OpinionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                generator = "opinion_SEQ")
    private Integer id;  //의견번호

    @Column(name = "comment", length = 200)
    private String comment;  //의견

    @ColumnDefault("0")
    @Column(name = "goodcnt")
    private Integer goodcnt;  //좋아요 수

    @ColumnDefault("0")
    @Column(name = "badcnt")
    private Integer badcnt;  //싫어요 수

    //토론주제와 의견을 연결하기 위한 토론번호 저장
    //의견의 내용이 존재해도 토론을 삭제할 수 있다.
    //토론삭제시 의견쓰레기 발생
    //@Column(name = "discussionid")
    //private Integer discussionid;

    //위에 내용을 보안하기 위해 외래키(연관관계) 적용
    //여러개의 의견이 하나의 토론과 관련
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussionid")  //discussionid와 discussionEntity의 id를 조인
    private DiscussionEntity discussionEntity;
}
