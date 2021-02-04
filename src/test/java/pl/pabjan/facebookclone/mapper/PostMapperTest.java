package pl.pabjan.facebookclone.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.repo.PostRepository;
import pl.pabjan.facebookclone.repo.UserRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class PostMapperTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostMapper postMapper;

    @Test
    @Transactional
    void shouldMapToDto() {

        //given
        Post newPost = new Post();
        newPost.setContent("test");
        newPost.setUser(userRepository.findById(1L).orElseThrow(() -> new FacebookCloneException("user not found")));
        postRepository.save(newPost);

        //when
        PostResponse postResponse = postMapper.mapToDto(newPost);

        //then
        assertThat(postResponse).isNotNull();
        assertThat(postResponse.getContent()).isEqualTo(newPost.getContent());
    }

    @Test
    void shouldMap() {

        //given
        PostRequest newPost = new PostRequest();
        newPost.setContent("test");
        User user = userRepository.findById(1L).orElseThrow(() -> new FacebookCloneException("user not found"));

        //when
        Post post = postMapper.map(newPost, user);

        //then
        assertThat(post).isNotNull();
        assertThat(post.getUser().getName()).isEqualTo(user.getName());
    }
}