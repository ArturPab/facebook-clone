package pl.pabjan.facebookclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.service.PostService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll(@RequestParam(required = false) Integer page) {
        return status(HttpStatus.OK).body(postService.findAll(page));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.findById(id));
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<List<PostResponse>> findByUserName(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.findByUser(id));
    }


    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/by-id/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody PostRequest post) {
        postService.edit(id, post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
