package com.zeros.domain.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.zeros.domain.entity.Post;
import com.zeros.domain.repository.MocksRepository.MockPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PostRepositoryTest extends AbstractRepositoryTest{


    @Autowired
    private PostRepository postRepository;

    @Test
    @DatabaseSetup("/repository/posts.xml")
    public void shouldMapWithDataBase(){
        Post one = postRepository.findOne(1L);
        Assertions.assertThat(one.getId()).isEqualTo(1L);
        Assertions.assertThat(one.getUsername()).isEqualTo("toto");
        Assertions.assertThat(one.getBody()).isEqualTo("test body");
    }

}