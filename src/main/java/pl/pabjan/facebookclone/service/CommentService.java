package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.facebookclone.controller.dto.CommentRequest;
import pl.pabjan.facebookclone.mapper.CommentMapper;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.CommentRepository;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;

    @Transactional
    public void save(CommentRequest commentRequest) {
        User user = authService.getCurrentUser();
        commentRepository.save(commentMapper.map(commentRequest, user));
    }
}
