package com.example.article.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Integer id;

    @NotEmpty(message="댓글은 생략할 수 없습니다.")
    private String body;
    private String nickname;
    private Integer articleid; //댓글을 삽입할 때 게시글 번호를 저장할 변수
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
