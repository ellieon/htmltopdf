package uk.co.vertigomoon.htmltopdf.controller;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class HomeControllerTest {

    @Autowired
    private MockMvc webClient;

    @Value("${application.github-redirect}")
    private String redirectUrl;

    @Test
    public void rootShouldRedirectToRepositoryHome(){
        assertThatCode(() ->
            webClient.perform(get("/"))
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl(redirectUrl))
        ).doesNotThrowAnyException();
    }

}


