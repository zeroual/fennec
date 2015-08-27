package com.zeros.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zeros.domain.entity.Member;
import com.zeros.domain.entity.Post;
import com.zeros.domain.repository.MemberRepository;
import com.zeros.domain.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest extends AbstractControllerTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;
    private SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor authenticatedUser;
    private Member poster;

    @Before
     public void initCurrentUser(){
         poster=new Member("zeros");
         memberRepository.save(poster);
         authenticatedUser = user("zeros");
         postRepository.deleteAll();
     }
    @Test
    public void shouldSaveTheNewPost() throws Exception {
        Post post = new Post("hello");
        mockMvc.perform(post(EndpointsPath.POST_PATH).with(authenticatedUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(post)))
                .andExpect(status().isCreated());
        Assertions.assertThat(postRepository.findOne(1L).getPoster().getId()).isEqualTo("zeros");
        Assertions.assertThat(postRepository.findOne(1L).getBody()).isEqualTo("hello");
    }
    @Test
    public void shouldFetchAllPosts() throws Exception {
        Post post1 = new Post("testBody1");
        post1.setPoster(poster);
        Post post2 = new Post("testBody2");
        post2.setPoster(poster);

        postRepository.save(post1);
        postRepository.save(post2);
        mockMvc.perform(get(EndpointsPath.POST_PATH).with(authenticatedUser))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("[{\"id\":1,\"body\":\"testBody1\",\"date\":\"2015-08-25\",\"poster\":{\"id\":\"zeros\",\"displayName\":null}},{\"id\":2,\"body\":\"testBody2\",\"date\":\"2015-08-25\",\"poster\":{\"id\":\"zeros\",\"displayName\":null}}]"));
    }

    private String toJson(Post post) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);
        return  json;
    }
}