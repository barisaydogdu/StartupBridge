package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private Long blog_id;
    private Long author_id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime created_at;
}