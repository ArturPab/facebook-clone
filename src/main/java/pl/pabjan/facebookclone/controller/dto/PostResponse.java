package pl.pabjan.facebookclone.controller.dto;


import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String content;
    private Instant created;
    private String name;
    private String lastName;
    private List<CommentResponse> comments;
}
