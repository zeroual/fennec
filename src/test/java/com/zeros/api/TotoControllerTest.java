package com.zeros.api;


import com.zeros.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class TotoControllerTest {

    private MockMvc mockMvc;
    @Test
    public void shouldReturnHello() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new TotoController()).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/hello-toto"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

    }
}