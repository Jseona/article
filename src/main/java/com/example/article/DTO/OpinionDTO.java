package com.example.article.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpinionDTO {
    private Integer id;  //의견번호
    private String comment;  //의견
    private Integer goodcnt;  //좋아요
    private Integer badcnt;  //싫어요
    private Integer discussionid;  //토론번호
    private LocalDateTime regDate;  //생성일자
    private LocalDateTime modDate;  //수정일자
}
