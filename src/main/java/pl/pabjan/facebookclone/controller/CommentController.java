package pl.pabjan.facebookclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pabjan.facebookclone.controller.dto.CommentRequest;
import pl.pabjan.facebookclone.service.CommentService;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> save(CommentRequest commentRequest) {
        commentService.save(commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
