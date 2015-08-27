package com.zeros.domain.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.zeros.domain.entity.Member;
import com.zeros.domain.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PostRepositoryTest extends AbstractRepositoryTest{


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DatabaseSetup("/repository/posts.xml")
    public void shouldMapWithDataBase(){
        Post one = postRepository.findOne(1L);
        Assertions.assertThat(one.getId()).isEqualTo(1L);
        Assertions.assertThat(one.getPoster().getId()).isEqualTo("12T");
        Assertions.assertThat(one.getBody()).isEqualTo("test body");
    }

    @Test
    @DatabaseSetup(value = {"/repository/empty.xml","/repository/members.xml"})
    public void shouldSavePost() {
        Member poster = memberRepository.findOne("12T");
        Post post = new Post("test body");
        post.setPoster(poster);
        Post savedPost = postRepository.save(post);
        Assertions.assertThat(savedPost.getId()).isEqualTo(1L);
        Assertions.assertThat(savedPost.getPoster().getId()).isEqualTo("12T");
        Assertions.assertThat(savedPost.getBody()).isEqualTo("test body");
    }
}