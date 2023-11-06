package com.example.article.Entity;

import lombok.*;

import javax.persistence.*;
/*
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name="테이블명")
//생성전략(이름, 테이블명, 시작값, 증가값)
//데이터베이스에서 직접 생성
//create sequence 테이블명_SEQ start with 1 increment by 1
@SequenceGenerator(
        name="생성전략명",
        sequenceName = "테이블명_SEQ",
        initialValue =1,
        allocationSize=1) //순차번호 생성
public class 참고Entity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "생성전략명")
    private Integer id;
    @Column(name="필드명", length="길이")
    private String 필드명;
}
*/