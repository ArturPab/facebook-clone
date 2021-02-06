package pl.pabjan.facebookclone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.pabjan.facebookclone.controller.dto.CommentRequest;
import pl.pabjan.facebookclone.controller.dto.PostResponse;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.model.Post;
import pl.pabjan.facebookclone.repo.CommentRepository;
import pl.pabjan.facebookclone.repo.PostRepository;
import pl.pabjan.facebookclone.repo.UserRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test@test.com", password = "polska123")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void shouldSaveCommentAndShowPostWithComment() throws Exception {

        //given
        Post newPost = new Post();
        newPost.setContent("test for comment");
        newPost.setUser(userRepository.findById(1L).orElseThrow(() -> new FacebookCloneException("Not found user")));
        postRepository.save(newPost);
        Integer postId = Math.toIntExact(newPost.getPostId());

        //when
        mockMvc.perform(post("/api/comments")
                .content("{\"content\": \"test1\", \"postId\": " + postId + " }"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/api/posts/by-id/" + newPost.getPostId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        //then
        PostResponse post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PostResponse.class);
        assertThat(post.getComments()).isNotNull();
    }

}