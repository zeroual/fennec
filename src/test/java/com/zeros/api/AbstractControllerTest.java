package com.zeros.api;

import com.zeros.config.DomainDevConfig;
import com.zeros.config.WebConfig;
import com.zeros.config.security.SecurityConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SecurityConfig.class, DomainDevConfig.class})
@WebAppConfiguration()
public abstract class AbstractControllerTest {
    @Autowired
    protected Filter springSecurityFilterChain;

    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;
    @Before
    public void setUp() throws SQLException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(springSecurityFilterChain)
                .build();
        resetAutoIncrement();
    }

    private void resetAutoIncrement() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement
                    .executeQuery("SELECT TABLE_NAME as name  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_CATALOG='TESTDB' AND TABLE_SCHEMA  ='PUBLIC'");
            Collection<String> tableNames = new ArrayList<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");
                if (!tableName.matches("(DATABASECHANGELOG.*|AUTHORITIES|USERS|USERPROFILE|USERCONNECTION)")) tableNames.add(tableName);
            }
            for (String tableName : tableNames) {
                statement.execute("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1");
            }
        }
    }
}
