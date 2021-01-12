package pl.pabjan.facebookclone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private String content;
    private Long postId;
}
