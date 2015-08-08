package com.zeros.security;

import com.zeros.config.security.SecurityConfig;
import com.zeros.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SecurityConfig.class})
@WebAppConfiguration()

public class AuthenticationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    public void shouldRedirectToLoginPage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldAuthenticate() throws Exception {
        mockMvc.perform(formLogin().user("zeros").password("zeros#password"))
                .andExpect(authenticated())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotAuthenticate() throws Exception {
        mockMvc.perform(formLogin().user("zeros").password("wrong password"))
                .andExpect(unauthenticated()).andExpect(status().isForbidden());
    }


}
