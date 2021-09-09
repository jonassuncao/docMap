package com.jassuncao.docmap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jassuncao.docmap.infra.ProfileApplication;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static com.jassuncao.docmap.web.ResponseUtils.DOCMAP_RESPONSE;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@SpringBootTest
@Transactional
@Profile(ProfileApplication.DEV)
@ExtendWith(SpringExtension.class)
public class IntegrationTests {

    protected MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    protected byte[] body(Serializable request) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(request).getBytes();
    }

    protected String headerMessage(ResultActions mock) {
        return mock.andReturn().getResponse().getHeader(DOCMAP_RESPONSE);
    }

    protected UUID id(ResultActions mock) throws UnsupportedEncodingException {
        final String response = StringUtils.substringBetween(mock.andReturn().getResponse().getContentAsString(), "\"");
        return UUID.fromString(response);
    }

    protected <T> T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }
}

