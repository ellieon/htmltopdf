package uk.co.vertigomoon.htmltopdf.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.co.vertigomoon.htmltopdf.model.RenderTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class PdfControllerTest {

    @Autowired
    private MockMvc webClient;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createHtmlFromTemplateGeneratesHtmlFromPebble() throws Exception {
        webClient.perform(post("/create-html-from-template")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(
                        new RenderTemplate("<html><body>{{var}}</body></html>",
                                Collections.singletonMap("var", "test")))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string(
                            containsString("<html><body>test</body></html>")));
    }

    @Test
    public void createHtmlFromTemplateShouldReturn422StatusCodeWhenGivenBadInput() throws Exception {
        webClient.perform(post("/create-html-from-template")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(
                        new RenderTemplate("<html><body>{{var}</bol>",
                                Collections.singletonMap("var", "replaced")))))
                    .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void createPdfFromHtmlReturnsPdfDocumentWhenGivenValidInput() throws Exception {
        webClient.perform(post("/create-from-html")
            .content("<html><body>hello</body></html>"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PDF_VALUE));
    }

    @Test
    public void createPdfFromHtmlShouldReturn422StatusCodeWhenGivenBadInput() throws Exception {
        webClient.perform(post("/create-from-html")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("<vkld;nvk;ldfa><vnkdfl;as>"))
                    .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void createPdfFromTemplate() throws Exception {
        webClient.perform(post("/create-from-template")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(
                        new RenderTemplate("<html><body>{{var}}</body></html>",
                                Collections.singletonMap("var", "replaced")))))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_PDF_VALUE));
    }

    @Test
    public void createPdfFromTemplateShouldReturn422StatusCodeWhenGivenBadInput() throws Exception {
        webClient.perform(post("/create-from-template")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(new RenderTemplate("<mvl;d'f>",
                        Collections.singletonMap("val", "replaced")))))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

}


