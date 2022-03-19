package uk.co.eclipsegroup.spring_rest_workshops.java;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class JavaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void javaVersions_canBeFetched() throws Exception {
        var javaVersions = objectMapper.readValue(mvc.perform(get("/java"))
                .andReturn()
                .getResponse()
                .getContentAsString(), new TypeReference<List<Java>>() {
        });

        assertThat(javaVersions).isNotEmpty();
    }
}