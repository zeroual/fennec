package com.zeros.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zeros.domain.entity.Post;
import com.zeros.domain.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerTest extends AbstractControllerTest {

    @Autowired
    private PostRepository postRepository;
    private SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor authenticatedUser;

     @Before
     public void initCurrentUser(){
        authenticatedUser = user("zeros").password("zeros#password");
         postRepository.deleteAll();
     }
    @Test
    public void shouldSaveTheNewPost() throws Exception {
        Post post = new Post("", "hello");
        mockMvc.perform(post(EndpointsPath.POST_PATH).with(authenticatedUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(post)))
                .andExpect(status().isCreated());
        Assertions.assertThat(postRepository.findOne(1L).getUsername()).isEqualTo("zeros");
        Assertions.assertThat(postRepository.findOne(1L).getBody()).isEqualTo("hello");
    }
    @Test
    public void shouldFetchAllPosts() throws Exception {
        postRepository.save(new Post("user1", "testBody1"));
        postRepository.save(new Post("user2", "testBody2"));
        mockMvc.perform(get(EndpointsPath.POST_PATH).with(authenticatedUser))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"username\":\"user1\",\"body\":\"testBody1\"},{\"id\":2,\"username\":\"user2\",\"body\":\"testBody2\"}]"));
    }

    private String toJson(Post post) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(post);
        return  json;
    }
}