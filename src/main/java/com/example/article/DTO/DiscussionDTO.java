package com.example.article.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscussionDTO {
    private Integer id;  //토론 번호
    @NotEmpty(message = "주제는 생략할 수 없습니다.")
    private String subject; //토론 주제
    private Integer viewcnt;  //조회 수
    private LocalDateTime regDate;  //생성일자
    private LocalDateTime modDate;  //수정일자
}
