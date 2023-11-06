package com.example.article.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="comment")
@SequenceGenerator(
        name="comment_SEQ",
        sequenceName = "comment_SEQ",
        initialValue =1,
        allocationSize=1) //순차번호 생성
//@EqualsAndHashCode(callSuper = true)// true의 경우 부모 클래스 필드 값들도 동일한지 체크하며, false(기본값)일 경우 자신 클래스의 필드 값만 고려한다.
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "comment_SEQ")
    private Integer id;

    @Column(name="body", length=100)
    private String body;  //댓글내용

    @Column(name="nickname", length=20)
    private String nickname; //작성자

    //부모, 자식 구분...현재 테이블의 위치
    //현재위치To대상위치
    //현재댓글들은 하나의 게시글에 존재가 가능
    @ManyToOne(fetch = FetchType.LAZY) //외래키  즉시 로딩과 지연 로딩(FetchType.LAZY or EAGER)
    @JoinColumn(name="articleid") //외래키는 joinColumn
    private ArticleEntity articleEntity; //해당참조 테이블을 선언

    //@Column(name="articleid")
    //private Integer articleid;
}
