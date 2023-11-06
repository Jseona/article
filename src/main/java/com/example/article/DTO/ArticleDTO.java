package com.example.article.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
    private Integer id;

    @NotEmpty(message="제목은 생략할 수 없습니다.")
    private String title;
    private String content;
    private Integer viewcnt;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
