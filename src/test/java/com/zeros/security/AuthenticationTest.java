package com.zeros.security;

import com.zeros.config.DomainDevConfig;
import com.zeros.config.security.SecurityConfig;
import com.zeros.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SecurityConfig.class, DomainDevConfig.class})
@WebAppConfiguration()
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})

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
                .andExpect(redirectedUrlPattern("**"+SecurityConfig.LOGIN_PAGE));
    }

    @Test
    public void shouldAuthenticateAndRedirectToHomePage() throws Exception {
        mockMvc.perform(formLogin().user("zeros").password("zeros#password"))
                .andExpect(authenticated())
                .andExpect(redirectedUrl(SecurityConfig.HOME_PAGE));
    }

    @Test
    public void shouldNotAuthenticateAndRedirectToLoginPage() throws Exception {
        mockMvc.perform(formLogin().user("zeros").password("wrong password"))
                .andExpect(unauthenticated())
                .andExpect(redirectedUrlPattern(SecurityConfig.LOGIN_PAGE + "?error"))
                .andDo(print());
    }
    @Test
    public void shouldLogout() throws Exception {
        mockMvc.perform(logout()).andExpect(unauthenticated());
    }
    @Test
    public void shouldRedirectToHomePageAfterLogout() throws Exception {
        mockMvc.perform(logout())
                .andExpect(unauthenticated())
                .andExpect(redirectedUrl(SecurityConfig.HOME_PAGE));
    }

}
