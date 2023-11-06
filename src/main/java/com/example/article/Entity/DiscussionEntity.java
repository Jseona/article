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
@Table(name="discussion")
@SequenceGenerator(name="discussion_SEQ", sequenceName = "discussion_SEQ",
        initialValue = 1, allocationSize = 1)
public class DiscussionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "discussion_SEQ")
    private Integer id;  //토론번호

    @Column(name = "subject", length = 200)
    private String subject;  //주제

    @ColumnDefault("0")  //기본값 0으로 설정
    @Column(name = "viewcnt")
    private Integer viewcnt;  //조회수
}
