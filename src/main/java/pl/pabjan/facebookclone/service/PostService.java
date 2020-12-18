package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.mapper.PostMapper;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.PostRepository;
import pl.pabjan.facebookclone.repo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final AuthService authService;

    public List<PostResponse> findAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public PostResponse findById(Long id) {
        return postMapper.mapToDto(postRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found post")));
    }


    public List<PostResponse> findByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new FacebookCloneException("Not found user"));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void save(PostRequest postRequest) {
        postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }
}
