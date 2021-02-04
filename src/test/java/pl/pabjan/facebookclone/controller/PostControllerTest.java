package pl.pabjan.facebookclone.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.pabjan.facebookclone.controller.dto.PostRequest;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.mapper.PostMapper;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.repo.PostRepository;
import pl.pabjan.facebookclone.repo.UserRepository;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test@test.com", password = "polska123")
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldFindAll() throws Exception {
        // given
        PostRequest newPost = new PostRequest();
        newPost.setContent("springboot test");
        postRepository.save(postMapper.map(newPost, userRepository.findById(1L).orElseThrow(() -> new FacebookCloneException("Not found user"))));

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @Test
    void shouldFindById() throws Exception {
        // given
        PostRequest postRequest = new PostRequest();
        postRequest.setContent("springboot test");
        Post newPost = postMapper.map(postRequest, userRepository.findById(1L).orElseThrow(() -> new FacebookCloneException("Not found user")));
        postRepository.save(newPost);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/by-id/" + newPost.getPostId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();


        // then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        assertThat(post).isNotNull();
        assertThat(post.getPostId()).isEqualTo(newPost.getPostId());
        postRepository.delete(newPost);
    }

    @Test
    void shouldFindByUserName() throws Exception {

        // given
        PostRequest postRequest = new PostRequest();
        postRequest.setContent("springboot test by user");
        Post newPost = postMapper.map(postRequest, userRepository.findById(1L).orElseThrow(() -> new FacebookCloneException("Not found user")));
        postRepository.save(newPost);

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/by-user/" + newPost.getUser().getUserId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        // then

        List<Post> posts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});
        assertThat(posts).isNotNull();
        postRepository.delete(newPost);
    }

}