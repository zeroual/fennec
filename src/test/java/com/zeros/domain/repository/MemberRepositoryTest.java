package com.zeros.domain.repository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.zeros.domain.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRepositoryTest extends AbstractRepositoryTest{


    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DatabaseSetup("/repository/members.xml")
    public void shouldMapWithDataBase(){
        Member one = memberRepository.findOne("12T");
        Assertions.assertThat(one.getId()).isEqualTo("12T");
        Assertions.assertThat(one.getDisplayName()).isEqualTo("toto");
    }

}